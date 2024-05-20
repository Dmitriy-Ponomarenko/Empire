package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed,
            upRightPressed, downRightPressed, upLeftPressed, downLeftPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if (upPressed && rightPressed) {
            upRightPressed = true;
        }
        if (upPressed && leftPressed) {
            upLeftPressed = true;
        }
        if (downPressed && rightPressed) {
            downRightPressed = true;
        }
        if (downPressed && leftPressed) {
            downLeftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = upLeftPressed = upRightPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = downRightPressed = downLeftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = upRightPressed = downRightPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = upLeftPressed = downLeftPressed = false;
        }
    }

}
