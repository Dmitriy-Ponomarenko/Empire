package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean upRightPressed, downRightPressed, upLeftPressed, downLeftPressed;
    public boolean sprint, sprintUpPressed, sprintDownPressed, sprintRightPressed, sprintLeftPressed; 

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
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
         
        // Sprint up
        if (code == KeyEvent.VK_SHIFT) {
        	sprint = true;
        	
        	if(upPressed) {
        		sprintUpPressed = true;
        	}
        }
        
        if (code == KeyEvent.VK_W) {
        	if (sprint) {
        		sprintUpPressed = true;
        	}
        }
        
        // Sprint down
        if (code == KeyEvent.VK_SHIFT) {
        	sprint = true;
        	
        	if(downPressed) {
        		sprintDownPressed = true;
        	}
        }
        
        if (code == KeyEvent.VK_S) {
        	if (sprint) {
        		sprintDownPressed = true;
        	}
        }
        
        // Sprint right
        if (code == KeyEvent.VK_SHIFT) {
        	sprint = true;
        	
        	if(rightPressed) {
        		sprintRightPressed = true;
        	}
        }
        
        if (code == KeyEvent.VK_D) {
        	if (sprint) {
        		sprintRightPressed = true;
        	}
        }
        
        // Sprint left
        if (code == KeyEvent.VK_SHIFT) {
        	sprint = true;
        	
        	if(leftPressed) {
        		sprintLeftPressed = true;
        	}
        }
        
        if (code == KeyEvent.VK_A) {
        	if (sprint) {
        		sprintLeftPressed = true;
        	}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = upLeftPressed = upRightPressed = sprintUpPressed = sprint = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = downRightPressed = downLeftPressed = sprintDownPressed = sprint = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = upRightPressed = downRightPressed = sprintRightPressed = sprint = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = upLeftPressed = downLeftPressed = sprintLeftPressed = sprint = false;
        }
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

}
