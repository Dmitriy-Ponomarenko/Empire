package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean upRightPressed, downRightPressed, upLeftPressed, downLeftPressed;
    public boolean sprint, sprintUpPressed, sprintDownPressed, sprintRightPressed, sprintLeftPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SHIFT && e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT) {
            sprint = true;
        }

        // WALK
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

        // Diagonal directions
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

        // if (code == KeyEvent.VK_ESCAPE) {
        // if (gp.gameState == gp.playState) {
        // gp.gameState = gp.pauseState;
        // } else if (gp.gameState == gp.pauseState) {
        // gp.gameState = gp.playState;
        // }
        // }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SHIFT && e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT) {
            sprint = false;
        }
        if (code == KeyEvent.VK_W) {
            upPressed = upLeftPressed = upRightPressed = sprintUpPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = downRightPressed = downLeftPressed = sprintDownPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = upRightPressed = downRightPressed = sprintRightPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = upLeftPressed = downLeftPressed = sprintLeftPressed = false;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}