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
	 * Zmienne uzywane do obslugi Graphics2D
	 */
	private enum Figure {
		ellipse, rectangle, generalPath
	}

	private final Map<Integer, Figure> queue;
	private Rectangle2D.Double cover;
	private final JFrame frame;
	public boolean isDrawPolyLine, isDrawEllipse, isDrawRectangle;
	private final GeneralList generalPaths;
	private final EllipseList ellipses;
	private final RectangleList rectangles;
	public boolean isFirstClick;
	private final static int MARGIN_OF_ERROR = 6;

	/**
	 * Kontruktor, sluzy do zaimplementowania Listenerow, zdefioniowaniu
	 * uzywanych list i mapy
	 * 
	 * @param frame
	 *            JFrame, do ktorego nalezy ten JPanel
	 */
	public Easel(JFrame frame) {
		this.frame = frame;
		generalPaths = new GeneralList();
		ellipses = new EllipseList();
		rectangles = new RectangleList();
		queue = new LinkedHashMap<>();
		ShapeAdapter adapter = new ShapeAdapter(this);
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		addMouseWheelListener(new ScaleHandler());
		cover = null;
	}

	/**
	 * Funkcja, ktora rysuje okreslone objekty w programie
	 * 
	 * @param graphics
	 *            Graphics
	 */
	private void Drawing(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics.create();
		RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics2d.setRenderingHints(renderingHints);
		int i = 0;
		int k = 0;
		int l = 0;
		for (Figure figure : queue.values()) {
			if (figure == Figure.ellipse) {
				Collections.reverse(ellipses);
				graphics2d.setColor(ellipses.get(i).getColor());
				graphics2d.fill(ellipses.get(i));
				Collections.reverse(ellipses);
				i++;
			}
			if (figure == Figure.rectangle) {
				Collections.reverse(rectangles);
				graphics2d.setColor(rectangles.get(k).getColor());
				graphics2d.fill(rectangles.get(k));
				k++;
				Collections.reverse(rectangles);
			}
			if (figure == Figure.generalPath) {
				Collections.reverse(generalPaths);
				if (generalPaths.get(l).isClosed()) {
					graphics2d.setColor(generalPaths.get(l).getColor());
					graphics2d.fill((Shape) generalPaths.get(l));
				} else {
					graphics2d.setPaint(Color.BLACK);
					for (int n = 0; n < generalPaths.get(l).points.size() - 1; n++) {
						graphics2d.drawLine((int) generalPaths.get(l).points.get(n).getX(),
								(int) generalPaths.get(l).points.get(n).getY(),
								(int) generalPaths.get(l).points.get(n + 1).getX(),
								(int) generalPaths.get(l).points.get(n + 1).getY());
					}
				}

				Collections.reverse(generalPaths);
				l++;
			}
		}
		float[] dash = { 4f, 0f, 2f };
		BasicStroke basicStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
		graphics2d.setStroke(basicStroke);
		graphics2d.setPaint(Color.BLUE);
		if (cover != null)
			graphics2d.draw(cover);
		BasicStroke bs1 = new BasicStroke();
		graphics2d.setStroke(bs1);
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
	private class ShapeAdapter extends MouseAdapter {
		private Figure draggedFigure;
		private int x;
		private int y;
		private final JPanel panelOwner;

		/**
		 * Konkstrukor potrzebny by klasa znala swojego JPamel wlasciciela
		 * 
		 * @param owner
		 *            wlasciciel klasy
		 */
		public ShapeAdapter(JPanel owner) {
			super();
			this.panelOwner = owner;
			draggedFigure = null;
		}

		/**
		 * Klasa oblugujaca zdarzenie "nacisinieto przycisk myszy" Sluzy do
		 * rysowania wielokata
		 * 
		 * @param event
		 *            obsluga zdarzenia
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			if (isDrawPolyLine) {
				double x = event.getX();
				double y = event.getY();
				if (isFirstClick) {
					queue.put(queue.size() + 1, Figure.generalPath);
					generalPaths.add(0,new NewGeneralPath(queue.size()));
					generalPaths.get(0).MoveTo(x, y);
					repaint();
					isFirstClick = false;
				} else {
					double relativeDistanceFromClick;
					relativeDistanceFromClick = generalPaths.get(0).points.get(0).getX() - x;
					if (Math.abs(relativeDistanceFromClick) < MARGIN_OF_ERROR
							&& Math.abs(generalPaths.get(0).points.get(0).getY() - y) < MARGIN_OF_ERROR) {
						generalPaths.get(0).lineTo(
								generalPaths.get(0).points.get(generalPaths.get(0).getAmountPnts() - 1).getX(),
								generalPaths.get(0).points.get(generalPaths.get(0).getAmountPnts() - 1).getY());
						generalPaths.get(0).closenPath();
						isDrawPolyLine = false;
					} else
						generalPaths.get(0).LineTo(x, y);
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
			if (isDrawEllipse) {
				queue.put(queue.size() + 1, Figure.ellipse);
				ellipses.add(0,new NewEllipse(event.getX(), event.getY(), 1, 1, queue.size()));
			}
			if (isDrawRectangle) {
				queue.put(queue.size() + 1, Figure.rectangle);
				rectangles.add(0,new NewRectangle(event.getX(), event.getY(), 1, 1, queue.size()));
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

			draggedFigure = null;
			Collections.reverse(ellipses);
			Collections.reverse(rectangles);
			Collections.reverse(generalPaths);
			int j = 0;
			int k = 0;
			int m = 0;
			ArrayList<Figure> iterator = new ArrayList<>();
			iterator.addAll(queue.values());
			for (Figure i : iterator) {
				if (i == Figure.rectangle) {
					if (rectangles.get(j).isHit(event.getX(), event.getY())) {
						if (event.isPopupTrigger()) {
							int how_many = queue.size();
							doPop(event, rectangles.get(j), panelOwner);
							if (how_many > queue.size())
								rectangles.remove(j);
							cover = rectangles.get(j).getCover();
							repaint();
						}
					}
					j++;
				}
				if (i == Figure.ellipse) {
					if (ellipses.get(k).isHit(event.getX(), event.getY())) {
						if (event.isPopupTrigger()) {
							int how_many = queue.size();
							doPop(event, ellipses.get(k), panelOwner);
							if (queue.size() < how_many)
								ellipses.remove(k);
							cover = ellipses.get(k).getCover();
							repaint();
						}
					}
					k++;
				}
				if (i == Figure.generalPath) {
					if (generalPaths.get(m).isHit(event.getX(), event.getY())) {
						if (event.isPopupTrigger()) {
							int how_many = queue.size();
							doPop(event, generalPaths.get(m), panelOwner);
							if (how_many > queue.size())
								generalPaths.remove(m);
							cover = generalPaths.get(m).getCover();
							repaint();
						}
					}
					m++;
				}
			}

			Collections.reverse(ellipses);
			Collections.reverse(rectangles);
			Collections.reverse(generalPaths);

			isDrawEllipse = false;
			isDrawRectangle = false;
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
			if (!(isDrawRectangle || isDrawEllipse)) {
				if (draggedFigure != null) {
					switch (draggedFigure) {
					case ellipse:
						for (NewEllipse ellipse : ellipses) {
							if (ellipse.isHit(x, y)) {
								ellipses.setFirst(ellipse);
								doMoveE(event, ellipse);
								cover = ellipse.getCover();
								repaint();
								return;
							}
						}
						break;
					case rectangle:
						for (NewRectangle rectangle : rectangles) {
							if (rectangle.isHit(x, y)) {
								rectangles.setFirst(rectangle);
								doMoveR(event, rectangle);
								cover = rectangle.getCover();
								repaint();
								return;
							}
						}
						break;
					case generalPath:
						for (NewGeneralPath general : generalPaths) {
							if (general.isClosed() && general.isHit(x, y)) {
								generalPaths.setFirst(general);
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
				ArrayList<Figure> iterator = new ArrayList<>();
				iterator.addAll(queue.values());
				Collections.reverse(iterator);
				for (Figure figure : iterator) {
					if (figure == Figure.ellipse) {
						NewEllipse eli = ellipses.get(j);
						if (eli.isHit(x, y)) {
							ellipses.setFirst(eli);
							doMoveE(event, eli);
							cover = eli.getCover();
							repaint();
							return;
						}
						j++;
					}
					if (figure == Figure.rectangle) {
						NewRectangle rec = rectangles.get(k);
						if (rec.isHit(x, y)) {
							rectangles.setFirst(rec);
							doMoveR(event, rec);
							cover = new Rectangle2D.Double(rec.getBounds2D().getX() - 1, rec.getBounds2D().getY() - 1,
									rec.getBounds2D().getWidth() + 1, rec.getBounds2D().getHeight() + 1);
							repaint();
							return;
						}
						k++;
					}
					if (figure == Figure.generalPath) {
						NewGeneralPath general = generalPaths.get(l);
						if (general.isClosed() && general.isHit(x, y)) {
							generalPaths.setFirst(general);
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
			if (isDrawEllipse) {
				ellipses.get(0).height = event.getY() - ellipses.get(0).y;
				ellipses.get(0).width = event.getX() - ellipses.get(0).x;
				repaint();
			}
			if (isDrawRectangle) {
				rectangles.get(0).height = event.getY() - rectangles.get(0).y;
				rectangles.get(0).width = event.getX() - rectangles.get(0).x;
				repaint();
			}
		}

		/**
		 * Funkcja sluzaca do obslugi PopupMenu dla okreslonej figury
		 * 
		 * @param event
		 *            obsluga zdarzenia
		 * @param rectangle
		 *            Prostokat dla ktorego wywyolujemy menu
		 * @param panel
		 *            panel na ktorym to sie odbywa
		 */
		private void doPop(MouseEvent event, NewRectangle rectangle, JPanel panel) {
			MyPopupMenu menu = new MyPopupMenu(frame, rectangle, panel, queue);
			menu.show(event.getComponent(), event.getX(), event.getY());
		}

		/**
		 * Funkcja sluzaca do obslugi PopupMenu dla oreslonej figury
		 * 
		 * @param event
		 *            obsluga zdarzenia
		 * @param ellipse
		 *            Elipsa dla ktorego wywyolujemy menu
		 * @param panel
		 *            panel na ktorym to sie odbywa
		 */
		private void doPop(MouseEvent event, NewEllipse ellipse, JPanel panel) {
			MyPopupMenu menu = new MyPopupMenu(frame, ellipse, panel, queue);
			menu.show(event.getComponent(), event.getX(), event.getY());
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
			MyPopupMenu menu = new MyPopupMenu(frame, general, panel, queue);
			menu.show(e.getComponent(), e.getX(), e.getY());
		}

		/**
		 * Funkcja sluzaca do przesuwania wielokatow
		 * 
		 * @param event
		 *            obsluga zdarzenia
		 * @param generalPath
		 *            wielokat, ktory przesuwamy
		 */
		private void doMoveGP(MouseEvent event, NewGeneralPath generalPath) {

			draggedFigure = Figure.generalPath;
			int relativeX = event.getX() - x;
			int relativeY = event.getY() - y;
			generalPath.transform(AffineTransform.getTranslateInstance(relativeX, relativeY));
			x += relativeX;
			y += relativeY;
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

			draggedFigure = Figure.ellipse;
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

			draggedFigure = Figure.rectangle;
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
		 * @param event
		 *            obsluga zdarzenia
		 */
		@Override
		public void mouseWheelMoved(MouseWheelEvent event) {

			int j = 0;
			int k = 0;
			int m = 0;

			ArrayList<Figure> figures = new ArrayList<>();
			figures.addAll(queue.values());
			Collections.reverse(figures);

			for (Figure figure : figures) {

				if (figure == Figure.rectangle) {
					NewRectangle rec = rectangles.get(j);
					if (rec.isHit(event.getX(), event.getY())) {
						doScale(event, rec);
						cover = rec.getCover();
						repaint();
						return;
					}
					j++;
				}
				if (figure == Figure.ellipse) {
					NewEllipse eli = ellipses.get(k);
					if (eli.isHit(event.getX(), event.getY())) {
						doScale(event, eli);
						cover = eli.getCover();
						repaint();
						return;
					}
					k++;
				}
				if (figure == Figure.generalPath) {
					NewGeneralPath general = generalPaths.get(m);
					if (general.isClosed() && general.isHit(event.getX(), event.getY())) {
						doScale(event, general);
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

		// zrobiæ funkcjê pracuj¹c¹ na klasie abstrakcyjnej
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
