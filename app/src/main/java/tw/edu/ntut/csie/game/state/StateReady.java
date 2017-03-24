package tw.edu.ntut.csie.game.state;

import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;

public class StateReady extends AbstractGameState {

//    private MovingBitmap _helpInfo;
//    private MovingBitmap _aboutInfo;
//    private MovingBitmap _background;
    private MovingBitmap _background;
    private MovingBitmap _menuInfo;
    private MovingBitmap _cardInfo;
    private MovingBitmap _gearInfo;
    private MovingBitmap _shopInfo;
    private MovingBitmap _museumInfo;
    private MovingBitmap _digInfo;

    private BitmapButton _menuButton;
    private BitmapButton _cardButton;
    private BitmapButton _gearButton;
    private BitmapButton _shopButton;
    private BitmapButton _museumButton;
    private BitmapButton _playButton;
    private BitmapButton _digButton;
    private BitmapButton _settingButton;

    private boolean _showCard;
    private boolean _showGear;
    private boolean _showShop;
    private boolean _showmuseum;
    private boolean _showDig;
    private boolean _showSetting;
//    private boolean _showHelp;
//    private boolean _showAbout;

    public StateReady(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
//        addGameObject(_helpInfo = new MovingBitmap(R.drawable.help_info));
////        addGameObject(_background = new MovingBitmap(R.drawable.state_ready));
//        addGameObject(_aboutInfo = new MovingBitmap(R.drawable.about_info));
//        addGameObject(_background = new MovingBitmap(R.drawable.digit_0));
//        addGameObject(_cardInfo = new MovingBitmap(R.drawable.digit_0));
//        addGameObject(_gearInfo = new MovingBitmap(R.drawable.digit_0));
//        addGameObject(_shopInfo = new MovingBitmap(R.drawable.digit_0));
//        addGameObject(_museumInfo = new MovingBitmap(R.drawable.digit_0));
        initializePlayButton();
        initializeCardButton();
        initializeShopButton();
        initializeGearButton();
        initializeMuseumButton();
//        setVisibility(false, false);
    }

    /**
     * ��l�ơyAbout�z�����s�C
     */
    // �}�o²��
    private void initializeMuseumButton() {
        addGameObject(_museumButton = new BitmapButton(R.drawable.digit_4, R.drawable.digit_0, 100, 170));
        _museumButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
//                setVisibility(false, true);
            }
        });
        addPointerEventHandler(_museumButton);
    }

    /**
     * ��l�ơyHelp�z�����s�C
     */
    // �C������
    private void initializeGearButton() {
        addGameObject(_gearButton = new BitmapButton(R.drawable.digit_2, R.drawable.digit_0, 100, 270));
        _gearButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
//                setVisibility(true, false);
            }
        });
        addPointerEventHandler(_gearButton);
    }

    /**
     * ��l�ơyMenu�z�����s�C
     */
    private void initializeShopButton() {
        addGameObject(_shopButton = new BitmapButton(R.drawable.digit_3, R.drawable.digit_0, 100, 220));
        _shopButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
//                setVisibility(false, false);
            }
        });
        addPointerEventHandler(_shopButton);
    }

    /**
     * ��l�ơyExit�z�����s�C
     */
    private void initializeCardButton() {
        addGameObject(_cardButton = new BitmapButton(R.drawable.digit_1, R.drawable.digit_0, 100, 320));
        _cardButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _engine.exit();
            }
        });
        addPointerEventHandler(_cardButton);
    }

    /**
     * ��l�ơyStart�z�����s�C
     */
    private void initializePlayButton() {
        addGameObject(_playButton = new BitmapButton(R.drawable.play, R.drawable.digit_0, 500, 120));
        _playButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.RUNNING_STATE);
            }
        });
        addPointerEventHandler(_playButton);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    /**
     * �]�w�e���W���ǹϤ�����ܡA���ǹϤ������áC
     *
//     * @param showHelp  ���Help�e��
//     * @param showAbout ���About�e��
     */
    private void setVisibility() {
//        _showHelp = showHelp;
//        _showAbout = showAbout;
        boolean showMenu = true;//!_showAbout && !_showHelp;
//        _helpInfo.setVisible(_showHelp);
//        _aboutInfo.setVisible(_showAbout);
        _background.setVisible(showMenu);

        _cardButton.setVisible(showMenu);
        _gearButton.setVisible(showMenu);
        _museumButton.setVisible(showMenu);
        _playButton.setVisible(showMenu);
        _shopButton.setVisible(!showMenu);
    }
}

