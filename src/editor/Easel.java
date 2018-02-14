package editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Easel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Kontruktor, sluzy do zaimplementowania Listenerow, zdefioniowaniu
	 * uzywanych list i mapy
	 * 
	 * @param frame
	 *            JFrame, do ktorego nalezy ten JPanel
	 */
	public Easel(JFrame frame) {
		myframe = frame;
		list_generalPath = new General_List();
		list_ellipse = new Ellipse_List();
		list_rectangle = new Rectangle_List();
		Queue = new LinkedHashMap<>();
		ShapeTestAdapter adapter = new ShapeTestAdapter(this);
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		addMouseWheelListener(new ScaleHandler());
		cover = null;
	}

	/**
	 * Zmienne uzywane do obslugi Graphics2D
	 */
	private enum what {
		ellipse, rectangle, generalPath
	}

	private final Map<Integer, what> Queue;
	private Rectangle2D.Double cover;
	private final JFrame myframe;
	public boolean ifdrawpolyline, ifdrawellipse, ifdrawrectangle;
	private final General_List list_generalPath;
	private final Ellipse_List list_ellipse;
	private final Rectangle_List list_rectangle;
	public boolean if_first_click;

	/**
	 * Funkcja, ktora rysuje okreslone objekty w programie
	 * 
	 * @param gp
	 *            Graphics
	 */
	private void Drawing(Graphics gp) {
		Graphics2D g2d = (Graphics2D) gp.create();
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		int i = 0;
		int k = 0;
		int l = 0;
		for (what j : Queue.values()) {
			if (j == what.ellipse) {
				Collections.reverse(list_ellipse);
				g2d.setColor(list_ellipse.get(i).getColor());
				g2d.fill(list_ellipse.get(i));
				Collections.reverse(list_ellipse);
				i++;
			}
			if (j == what.rectangle) {
				Collections.reverse(list_rectangle);
				g2d.setColor(list_rectangle.get(k).getColor());
				g2d.fill(list_rectangle.get(k));
				k++;
				Collections.reverse(list_rectangle);
			}
			if (j == what.generalPath) {
				Collections.reverse(list_generalPath);
				if (list_generalPath.get(l).isClosed()) {
					g2d.setColor(list_generalPath.get(l).getColor());
					g2d.fill((Shape) list_generalPath.get(l));
				} else {
					g2d.setPaint(Color.BLACK);
					for (int n = 0; n < list_generalPath.get(l).points.size() - 1; n++) {
						g2d.drawLine((int) list_generalPath.get(l).points.get(n).getX(),
								(int) list_generalPath.get(l).points.get(n).getY(),
								(int) list_generalPath.get(l).points.get(n + 1).getX(),
								(int) list_generalPath.get(l).points.get(n + 1).getY());
					}
				}

				Collections.reverse(list_generalPath);
				l++;
			}
		}
		float[] dash3 = { 4f, 0f, 2f };
		BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f);
		g2d.setStroke(bs3);
		g2d.setPaint(Color.BLUE);
		if (cover != null)
			g2d.draw(cover);
		BasicStroke bs1 = new BasicStroke();
		g2d.setStroke(bs1);
	}

	/**
	 * Przeciazona funkcja paintComponent
	 * 
	 * @param gp
	 *            Graphics do rysowania
	 */
	@Override
	public void paintComponent(Graphics gp) {
		super.paintComponent(gp);
		Drawing(gp);
	}

	/**
	 * Klasa do obslugi zdarzen
	 */
	private class ShapeTestAdapter extends MouseAdapter {
		private what dragged;
		private int x;
		private int y;
		private final JPanel owner;

		/**
		 * Konkstrukor potrzebny by klasa znala swojego JPamel wlasciciela
		 * 
		 * @param owner
		 *            wlasciciel klasy
		 */
		public ShapeTestAdapter(JPanel owner) {
			super();
			this.owner = owner;
			dragged = null;
		}

		/**
		 * Klasa oblugujaca zdarzenie "nacisinieto przycisk myszy" Sluzy do
		 * rysowania wielokata
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			if (ifdrawpolyline) {
				double x = e.getX();
				double y = e.getY();
				if (if_first_click) {
					Queue.put(Queue.size() + 1, what.generalPath);
					list_generalPath.add(new NewGeneralPath(Queue.size()));
					list_generalPath.get(list_generalPath.size() - 1).MoveTo(x, y);
					list_generalPath.setFirst(list_generalPath.size() - 1);
					repaint();
					if_first_click = false;
				} else {
					double t;
					t = list_generalPath.get(0).points.get(0).getX() - x;
					if (Math.abs(t) < 6 && Math.abs(list_generalPath.get(0).points.get(0).getY() - y) < 6) {
						list_generalPath.get(0).lineTo(
								list_generalPath.get(0).points.get(list_generalPath.get(0).getAmountPnts() - 1).getX(),
								list_generalPath.get(0).points.get(list_generalPath.get(0).getAmountPnts() - 1).getY());
						list_generalPath.get(0).closenPath();
						ifdrawpolyline = false;
					} else
						list_generalPath.get(0).LineTo(x, y);
					repaint();
				}
			}
		}

		/**
		 * Przeciazona funkcja nacisnietej myszy. Sluzy go³wnie do rysowania
		 * elipsy i prostokata
		 * 
		 * @param event
		 *            obsluga zdazenia
		 */
		@Override
		public void mousePressed(MouseEvent event) {
			x = event.getX();
			y = event.getY();
			cover = null;
			if (ifdrawellipse) {
				Queue.put(Queue.size() + 1, what.ellipse);
				list_ellipse.add(new NewEllipse(event.getX(), event.getY(), 1, 1, Queue.size()));
				list_ellipse.setFirst(list_ellipse.size() - 1);
			}
			if (ifdrawrectangle) {
				Queue.put(Queue.size() + 1, what.rectangle);
				list_rectangle.add(new NewRectangle(event.getX(), event.getY(), 1, 1, Queue.size()));
				list_rectangle.setFirst(list_rectangle.size() - 1);
			}
			repaint();
		}

		/**
		 * Przeciazona funkcja Released sluzy do uruchamiania PopupMenu
		 * 
		 * @param event
		 *            obluga zdazenia
		 */
		@Override
		public void mouseReleased(MouseEvent event) {

			dragged = null;
			Collections.reverse(list_ellipse);
			Collections.reverse(list_rectangle);
			Collections.reverse(list_generalPath);
			int j = 0;
			int k = 0;
			int m = 0;
			ArrayList<what> iterator = new ArrayList<>();
			iterator.addAll(Queue.values());
			for (what i : iterator) {
				if (i == what.rectangle) {
					if (list_rectangle.get(j).isHit(event.getX(), event.getY())) {
						if (event.isPopupTrigger()) {
							int how_many = Queue.size();
							doPop(event, list_rectangle.get(j), owner);
							if (how_many > Queue.size())
								list_rectangle.remove(j);
							cover = list_rectangle.get(j).getCover();
							repaint();
						}
					}
					j++;
				}
				if (i == what.ellipse) {
					if (list_ellipse.get(k).isHit(event.getX(), event.getY())) {
						if (event.isPopupTrigger()) {
							int how_many = Queue.size();
							doPop(event, list_ellipse.get(k), owner);
							if (Queue.size() < how_many)
								list_ellipse.remove(k);
							cover = list_ellipse.get(k).getCover();
							repaint();
						}
					}
					k++;
				}
				if (i == what.generalPath) {
					if (list_generalPath.get(m).isHit(event.getX(), event.getY())) {
						if (event.isPopupTrigger()) {
							int how_many = Queue.size();
							doPop(event, list_generalPath.get(m), owner);
							if (how_many > Queue.size())
								list_generalPath.remove(m);
							cover = list_generalPath.get(m).getCover();
							repaint();
						}
					}
					m++;
				}
			}

			Collections.reverse(list_ellipse);
			Collections.reverse(list_rectangle);
			Collections.reverse(list_generalPath);

			ifdrawellipse = false;
			ifdrawrectangle = false;
			repaint();
		}

		/**
		 * Przeciazona funkcja przesuwania myszy sluzy do przesuwania figur
		 * 
		 * @param event
		 *            obsluga zdarzenia
		 */
		@Override
		public void mouseDragged(MouseEvent event) {
			if (!(ifdrawrectangle || ifdrawellipse)) {
				if (dragged != null) {
					switch (dragged) {
					case ellipse:
						for (NewEllipse eli : list_ellipse) {
							if (eli.isHit(x, y)) {
								list_ellipse.setFirst(eli);
								Queue.remove(eli.nr);
								Queue.put(eli.nr, what.ellipse);
								doMoveE(event, eli);
								cover = eli.getCover();
								repaint();
								return;
							}
						}
						break;
					case rectangle:
						for (NewRectangle rec : list_rectangle) {
							if (rec.isHit(x, y)) {
								Queue.remove(rec.nr);
								Queue.put(rec.nr, what.rectangle);
								list_rectangle.setFirst(rec);
								doMoveR(event, rec);
								cover = rec.getCover();
								repaint();
								return;
							}
						}
						break;
					case generalPath:
						for (NewGeneralPath general : list_generalPath) {
							if (general.isClosed() && general.isHit(x, y)) {
								list_generalPath.setFirst(general);
								Queue.remove(general.nr);
								Queue.put(general.nr, what.generalPath);
								doMoveGP(event, general);
								cover = general.getCover();
								repaint();
								return;
							}
						}
						break;
					default:
					}
				}
				int j = 0;
				int k = 0;
				int l = 0;
				ArrayList<what> iterator = new ArrayList<>();
				iterator.addAll(Queue.values());
				Collections.reverse(iterator);
				for (what i : iterator) {
					if (i == what.ellipse) {
						NewEllipse eli = list_ellipse.get(j);
						if (eli.isHit(x, y)) {
							list_ellipse.setFirst(eli);
							doMoveE(event, eli);
							cover = eli.getCover();
							repaint();
							return;
						}
						j++;
					}
					if (i == what.rectangle) {
						NewRectangle rec = list_rectangle.get(k);
						if (rec.isHit(x, y)) {
							list_rectangle.setFirst(rec);
							doMoveR(event, rec);
							cover = new Rectangle2D.Double(rec.getBounds2D().getX() - 1, rec.getBounds2D().getY() - 1,
									rec.getBounds2D().getWidth() + 1, rec.getBounds2D().getHeight() + 1);
							repaint();
							return;
						}
						k++;
					}
					if (i == what.generalPath) {
						NewGeneralPath general = list_generalPath.get(l);
						if (general.isClosed() && general.isHit(x, y)) {
							list_generalPath.setFirst(general);
							doMoveGP(event, general);
							cover = new Rectangle2D.Double(general.getBounds2D().getX(), general.getBounds2D().getY(),
									general.getBounds2D().getWidth() + 1, general.getBounds2D().getHeight() + 1);
							repaint();
							return;
						}
						l++;
					}
				}
			}
			if (ifdrawellipse) {
				list_ellipse.get(0).height = event.getY() - list_ellipse.get(0).y;
				list_ellipse.get(0).width = event.getX() - list_ellipse.get(0).x;
				repaint();
			}
			if (ifdrawrectangle) {
				list_rectangle.get(0).height = event.getY() - list_rectangle.get(0).y;
				list_rectangle.get(0).width = event.getX() - list_rectangle.get(0).x;
				repaint();
			}
		}

		/**
		 * Funkcja sluzaca do obslugi PopupMenu dla okreslonej figury
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 * @param rec
		 *            Prostokat dla ktorego wywyolujemy menu
		 * @param panel
		 *            panel na ktorym to sie odbywa
		 */
		private void doPop(MouseEvent e, NewRectangle rec, JPanel panel) {
			MyPopupMenu menu = new MyPopupMenu(myframe, rec, panel, Queue);
			menu.show(e.getComponent(), e.getX(), e.getY());
		}

		/**
		 * Funkcja sluzaca do obslugi PopupMenu dla oreslonej figury
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 * @param eli
		 *            Elipsa dla ktorego wywyolujemy menu
		 * @param panel
		 *            panel na ktorym to sie odbywa
		 */
		private void doPop(MouseEvent e, NewEllipse eli, JPanel panel) {
			MyPopupMenu menu = new MyPopupMenu(myframe, eli, panel, Queue);
			menu.show(e.getComponent(), e.getX(), e.getY());
		}

		/**
		 * Funkcja sluzaca do obslugi PopupMenu dla oreslonej figury
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 * @param general
		 *            Wielokat dla ktorego wywyolujemy menu
		 * @param panel
		 *            panel na ktorym to sie odbywa
		 */
		private void doPop(MouseEvent e, NewGeneralPath general, JPanel panel) {
			MyPopupMenu menu = new MyPopupMenu(myframe, general, panel, Queue);
			menu.show(e.getComponent(), e.getX(), e.getY());
		}

		/**
		 * Funkcja sluzaca do przesuwania wielokatow
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 * @param general
		 *            wielokat, ktory przesuwamy
		 */
		private void doMoveGP(MouseEvent e, NewGeneralPath general) {

			dragged = what.generalPath;
			int dx = e.getX() - x;
			int dy = e.getY() - y;
			general.transform(AffineTransform.getTranslateInstance(dx, dy));
			x += dx;
			y += dy;
		}

		/**
		 * Funkcja sluzaca do przesuwania elips
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 * @param eli
		 *            elipsa, ktora przesuwamy
		 */
		private void doMoveE(MouseEvent e, NewEllipse eli) {

			dragged = what.ellipse;
			int dx = e.getX() - x;
			int dy = e.getY() - y;

			eli.x += dx;
			eli.y += dy;
			x += dx;
			y += dy;
		}

		/**
		 * Funkcja sluzaca do przesuwania prostokatow
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 * @param rec
		 *            prostokat, ktory przesuwamy
		 */
		private void doMoveR(MouseEvent e, NewRectangle rec) {

			dragged = what.rectangle;
			int dx = e.getX() - x;
			int dy = e.getY() - y;

			rec.addX(dx);
			rec.addY(dy);
			repaint();
			x += dx;
			y += dy;
		}
	}

	/**
	 * Klasa do obslugi Scrolla
	 */
	class ScaleHandler implements MouseWheelListener {

		/**
		 * Funkcja do obslugi krecenia scrollem
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 */
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {

			int j = 0;
			int k = 0;
			int m = 0;

			ArrayList<what> iterator = new ArrayList<>();
			iterator.addAll(Queue.values());
			Collections.reverse(iterator);

			for (what i : iterator) {

				if (i == what.rectangle) {
					NewRectangle rec = list_rectangle.get(j);
					if (rec.isHit(e.getX(), e.getY())) {
						doScale(e, rec);
						cover = rec.getCover();
						repaint();
						return;
					}
					j++;
				}
				if (i == what.ellipse) {
					NewEllipse eli = list_ellipse.get(k);
					if (eli.isHit(e.getX(), e.getY())) {
						doScale(e, eli);
						cover = eli.getCover();
						repaint();
						return;
					}
					k++;
				}
				if (i == what.generalPath) {
					NewGeneralPath general = list_generalPath.get(m);
					if (general.isClosed() && general.isHit(e.getX(), e.getY())) {
						doScale(e, general);
						cover = general.getCover();
						repaint();
						return;
					}
					m++;
				}

			}
		}

		/**
		 * Funkcja skalujaca prostokat
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 * @param rec
		 *            prostokat, ktory skalujemy
		 */
		private void doScale(MouseWheelEvent e, NewRectangle rec) {

			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

				float amount = e.getWheelRotation() * 5f;
				rec.addWidth(amount);
				rec.addHeight(amount);
				repaint();

			}
		}
//zrobiæ funkcjê pracuj¹c¹ na klasie abstrakcyjnej
		/**
		 * Funkcja skalujaca elipse
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 * @param eli
		 *            elipsa, ktory skalujemy
		 */
		private void doScale(MouseWheelEvent e, NewEllipse eli) {

			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

				float amount = e.getWheelRotation() * 5f;
				eli.addWidth(amount);
				eli.addHeight(amount);
				repaint();

			}
		}

		/**
		 * Funkcja skalujaca wielokat
		 * 
		 * @param e
		 *            obsluga zdarzenia
		 * @param general
		 *            wielokat, ktory skalujemy
		 */
		private void doScale(MouseWheelEvent e, NewGeneralPath general) {

			int x = e.getX();
			int y = e.getY();

			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

				float amount = e.getWheelRotation() * 0.1f + 1;
				AffineTransform trans = new AffineTransform();
				trans.setToTranslation(general.getBounds().getX(), general.getBounds().getY());
				trans.scale(amount, amount);
				trans.translate(-general.getBounds().getX(), -general.getBounds().getY());
				general.transform(trans);
				repaint();

			}
		}
	}
}
