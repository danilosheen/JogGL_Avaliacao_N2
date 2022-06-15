import com.jogamp.newt.event.KeyEvent;
import com.sun.opengl.util.Animator;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class JogGL implements GLEventListener {

	private static float y1 = 0.5f;
	private static float y2 = -0.5f;
	private static float v = 0.01f;

	private Animator a;
	private GL2 gl;

	public static void main(String[] args) {
		final GLProfile gp = GLProfile.get(GLProfile.GL2);
		GLCapabilities cap = new GLCapabilities(gp);

		final GLCanvas gc = new GLCanvas(cap);
		JogGL bl = new JogGL();
		gc.addGLEventListener(bl);
		gc.setSize(400, 400);

		final JFrame frame = new JFrame("JOGL Line");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {

				if (evt.getKeyCode() == KeyEvent.VK_UP) {
					animador2();
				} else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

					if (y1 == 1.937151E-7f) {
						System.out.println("if1 " + y1);
						y1 += 0;
						y2 += 0;
					} else if (y1 != 1.937151E-7f) {
						animador1();
					}
				}
			}
		});

		frame.add(gc);
		frame.setSize(500, 400);
		frame.setVisible(true);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		a = new Animator(drawable);
	}

	@Override
	public void dispose(GLAutoDrawable glad) {

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(1f, 1f, 1f);
		// linha1
		gl.glVertex2d(-0.5, y1);
		gl.glVertex2d(0.5, y1);

		// linha2
		gl.glVertex2d(-0.5, y2);
		gl.glVertex2d(0.5, y2);
		gl.glEnd();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int i, int i1, int i2, int i3) {
		deslocar();
	}

	public static void animador1() {

		y1 -= v;
		y2 += v;

	}

	public static void animador2() {

		y1 += v;
		y2 -= v;

	}

	private void deslocar() {
		new Thread(() -> {

			try {
				Thread.sleep(200);
			} catch (InterruptedException ex) {

			}
		});
		a.start();
	}

}
