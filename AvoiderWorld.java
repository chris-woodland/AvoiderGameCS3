import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AvoiderWorld extends World {
    private GreenfootSound bgm;
    private Counter scoreBoard;
    private Counter levelCounter;
    private int enemySpawnRate = 20;
    private int enemySpeed = 2;
    private int currentLevel = 1;
    private int nextLevel = 30;
    private int cupcakeFrequency = 10;
    private int cloverFrequency = 10;
    private int rockFrequency = 1;

    public AvoiderWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 

        // Initialize the music
        bgm = new GreenfootSound("sounds/bgm.mp3"); // Music credit: Contra (NES) by Konami
        bgm.playLoop(); // Play the music

        // Add the player and scoreboard to the world
        prepare();
    }
    private void prepare()
    {
        Avatar avatar = new Avatar();
        addObject(avatar, getWidth() / 2, getHeight() / 2);
        scoreBoard = new Counter("Score: ");
        addObject(scoreBoard, 70, 50);
        levelCounter = new Counter("Level: ");
        levelCounter.setValue(1);
        addObject(levelCounter, 70, 20);
    }
    public void act() {
        // Randomly add enemies to the world
        if(Greenfoot.getRandomNumber(1000) < 20) {
            Enemy e = new Enemy();
            e.setSpeed(enemySpeed);
            addObject(e, Greenfoot.getRandomNumber(getWidth() - 20)+ 10, -30);
            // Give us some points for facing yet another enemy
            scoreBoard.setValue(scoreBoard.getValue() + 1);
        }
        generateStars();
        increaseLevel();
        generatePowerItems();
    }
    private void generatePowerItems() {
        generatePowerItem(0, cupcakeFrequency); // new Cupcake
    }
    private void generatePowerItem(int type, int freq) {
         if( Greenfoot.getRandomNumber(1000) < freq ) {
             int targetX = Greenfoot.getRandomNumber(
             getWidth() -80) + 40;
             int targetY = Greenfoot.getRandomNumber(
             getHeight()/2) + 20;
             Actor a = createPowerItem(type, targetX, targetY, 100);
             if( Greenfoot.getRandomNumber(100) < 50) {
                 addObject(a, getWidth() + 20,
                 Greenfoot.getRandomNumber(getHeight()/2) + 30);
                } else {
                    addObject(a, -20,
                    Greenfoot.getRandomNumber(getHeight()/2) + 30);
                }
         }
    }
    private Actor createPowerItem(int type, int targetX, int targetY, int expireTime) {
        switch(type) {
            case 0: return new Cupcake(targetX, targetY,
            expireTime);
        }
        return null;
    }
    private void generateStars() {
        if( Greenfoot.getRandomNumber(1000) < 350) {
            Star s = new Star();
            GreenfootImage image = s.getImage();
        if( Greenfoot.getRandomNumber(1000) < 300) {
            // this is a close bright star
            s.setSpeed(3);
            image.setTransparency(
            Greenfoot.getRandomNumber(25) + 225);
            image.scale(4,4);
        } else if ( Greenfoot.getRandomNumber(1000) < 200){
            
            s.setSpeed(6);
            image.setTransparency(
            Greenfoot.getRandomNumber(10) + 125);
            image.scale(2,5);
        } else {
            // this is a further dim star
            s.setSpeed(2);
            image.setTransparency(
            Greenfoot.getRandomNumber(50) + 100);
            image.scale(2,2);
        }
        s.setImage(image);
        addObject( s, -1, Greenfoot.getRandomNumber(getWidth()-20)+10);
        }   
    }


    public void endGame() {
        bgm.stop();
        AvoiderGameOverWorld go = new AvoiderGameOverWorld();
        Greenfoot.setWorld(go);
    }
    
    private void increaseLevel() {
        int score = scoreBoard.getValue();
        if(score >= nextLevel) {
            enemySpawnRate += 2;
            enemySpeed++;
            currentLevel++;
            levelCounter.setValue(levelCounter.getValue() + 1);
            cupcakeFrequency += 3;
            nextLevel += 50;
        }
    } 
}
