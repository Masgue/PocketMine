package tw.edu.ntut.csie.game.state;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.card.CardAttributes;
import tw.edu.ntut.csie.game.card.character.DurabilityBonus;
import tw.edu.ntut.csie.game.card.mine.CoalBonus;
import tw.edu.ntut.csie.game.card.tool.BombBonus;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;
import tw.edu.ntut.csie.game.card.Card;

public class StateReady extends AbstractGameState {
    private MovingBitmap _menuInfo;
    private MovingBitmap _cardInfo;
    private MovingBitmap _gearInfo;
    private MovingBitmap _shopInfo;
    private MovingBitmap _museumInfo;
    private MovingBitmap _digInfo;
    private MovingBitmap _settingInfo;

    private BitmapButton _menuButton;
    private BitmapButton _cardButton;
    private BitmapButton _gearButton;
    private BitmapButton _shopButton;
    private BitmapButton _museumButton;
    private BitmapButton _playButton;
    private BitmapButton _changeCardsButton;
    private BitmapButton _digButton;
    private BitmapButton _settingButton;
    private BitmapButton _backButton;
    private BitmapButton _exitButton;

    private Card _firstCard;
    private Card _secondCard;
    private Card _thirdCard;

    private boolean _showMenu;
    private boolean _showCard;
    private boolean _showGear;
    private boolean _showShop;
    private boolean _showMuseum;
    private boolean _showDig;
    private boolean _showSetting;
    private boolean _showFirstCard;
    private boolean _showSecondCard;
    private boolean _showThirdCard;
    private boolean _pressFirstCard;
    private boolean _pressSecondCard;
    private boolean _pressThirdCard;

    public StateReady(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        addGameObject(_menuInfo = new MovingBitmap(R.drawable.menubackground));
        addGameObject(_cardInfo = new MovingBitmap(R.drawable.cardbackground));
        addGameObject(_gearInfo = new MovingBitmap(R.drawable.gearbackground));
        addGameObject(_shopInfo = new MovingBitmap(R.drawable.shopbackground));
        addGameObject(_museumInfo = new MovingBitmap(R.drawable.museumbackground));
        addGameObject(_digInfo = new MovingBitmap(R.drawable.digbackground));
        addGameObject(_settingInfo = new MovingBitmap(R.drawable.settingbackground));
        InitializeCards();
        InitializeMenuButton();
        initializeCardButton();
        initializeShopButton();
        initializeGearButton();
        initializeMuseumButton();
        initializePlayButton();
        InitializeChangeCardsButton();
        InitializeDigButton();
        InitializeSettingButton();
        InitializeBackButton();
        InitializeExitButton();
        _showMenu = true;
        _showCard = false;
        _showGear = false;
        _showShop = false;
        _showMuseum = false;
        _showDig = false;
        _showSetting = false;
        _showFirstCard = false;
        _showSecondCard = false;
        _showThirdCard = false;
        _pressFirstCard = false;
        _pressSecondCard = false;
        _pressThirdCard = false;
        setVisibility();
    }

    private void InitializeCards() {
        InitializeFirstCard();
        InitializeSecondCard();
        InitializeThirdCard();
    }

    private void InitializeFirstCard() {
        _firstCard = new DurabilityBonus();
        _firstCard.SetBitmapButton(new BitmapButton(R.drawable.block0_invisible, 100, 170));
        addGameObject(_firstCard.GetBitmapButton());
        _firstCard.GetBitmapButton().addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _pressFirstCard = !_pressFirstCard;
                setVisibility();
            }
        });
        addPointerEventHandler(_firstCard.GetBitmapButton());
    }

    private void InitializeSecondCard() {
        _secondCard = new CoalBonus();
        _secondCard.SetBitmapButton(new BitmapButton(R.drawable.block1_unbreakable, 100, 100));
        addGameObject(_secondCard.GetBitmapButton());
        _secondCard.GetBitmapButton().addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _pressSecondCard = !_pressSecondCard;
                setVisibility();
            }
        });
        addPointerEventHandler(_secondCard.GetBitmapButton());
    }

    private void InitializeThirdCard() {
        _thirdCard = new BombBonus();
        _thirdCard.SetBitmapButton(new BitmapButton(R.drawable.block2_dirt, 100, 30));
        addGameObject(_thirdCard.GetBitmapButton());
        _thirdCard.GetBitmapButton().addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _pressThirdCard = !_pressThirdCard;
                setVisibility();
            }
        });
        addPointerEventHandler(_thirdCard.GetBitmapButton());
    }

    private void InitializeMenuButton() {
        addGameObject(_menuButton = new BitmapButton(R.drawable.menu, 65, 290));
        _menuButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _showMenu = true;
                _showCard = false;
                _showGear = false;
                _showShop = false;
                _showMuseum = false;
                _showDig = false;
                _showSetting = false;
                setVisibility();
            }
        });
        addPointerEventHandler(_menuButton);
    }

    private void initializeCardButton() {
        addGameObject(_cardButton = new BitmapButton(R.drawable.card, 65, 225));
        _cardButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _showMenu = false;
                _showCard = true;
                _showGear = false;
                _showShop = false;
                _showMuseum = false;
                _showDig = false;
                _showSetting = false;
                setVisibility();
            }
        });
        addPointerEventHandler(_cardButton);
    }

    private void initializeGearButton() {
        addGameObject(_gearButton = new BitmapButton(R.drawable.gear, 65, 160));
        _gearButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _showMenu = false;
                _showCard = false;
                _showGear = true;
                _showShop = false;
                _showMuseum = false;
                _showDig = false;
                _showSetting = false;
                setVisibility();
            }
        });
        addPointerEventHandler(_gearButton);
    }

    private void initializeMuseumButton() {
        addGameObject(_museumButton = new BitmapButton(R.drawable.museum, 65, 95));
        _museumButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _showMenu = false;
                _showCard = false;
                _showGear = false;
                _showShop = false;
                _showMuseum = true;
                _showDig = false;
                _showSetting = false;
                setVisibility();
            }
        });
        addPointerEventHandler(_museumButton);
    }

    private void initializeShopButton() {
        addGameObject(_shopButton = new BitmapButton(R.drawable.shop, 65, 30));
        _shopButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _showMenu = false;
                _showCard = false;
                _showGear = false;
                _showShop = true;
                _showMuseum = false;
                _showDig = false;
                _showSetting = false;
                setVisibility();
            }
        });
        addPointerEventHandler(_shopButton);
    }

    private void initializePlayButton() {
        addGameObject(_playButton = new BitmapButton(R.drawable.play, 500, 10));
        _playButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _showMenu = false;
                _showCard = false;
                _showGear = false;
                _showShop = false;
                _showMuseum = false;
                _showDig = true;
                _showSetting = false;
                _showFirstCard = true;
                _showSecondCard = true;
                _showThirdCard = true;
                setVisibility();
            }
        });
        addPointerEventHandler(_playButton);
    }

    private void InitializeChangeCardsButton() {
        addGameObject(_changeCardsButton = new BitmapButton(R.drawable.block0_invisible, 400, 50));
        _changeCardsButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {

            }
        });
        addPointerEventHandler(_changeCardsButton);
    }

    private void InitializeDigButton() {
        addGameObject(_digButton = new BitmapButton(R.drawable.dig, 500, 10));
        _digButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                changeState(Game.RUNNING_STATE, CreateMap());
            }
        });
        addPointerEventHandler(_digButton);
    }

    private Map<String, Object> CreateMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        ArrayList<CardAttributes> cardAttributes = new ArrayList<CardAttributes>();
        cardAttributes.add(_firstCard.GetCardAttributes());
        cardAttributes.add(_secondCard.GetCardAttributes());
        cardAttributes.add(_thirdCard.GetCardAttributes());
        map.put("CardAttributes", cardAttributes);
        return map;
    }

    private void InitializeSettingButton() {
        addGameObject(_settingButton = new BitmapButton(R.drawable.setting_pressed, 0, 0));
        _settingButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _showMenu = false;
                _showCard = false;
                _showGear = false;
                _showShop = false;
                _showMuseum = false;
                _showDig = false;
                _showSetting = true;
                _showFirstCard = false;
                _showSecondCard = false;
                _showThirdCard = false;
                setVisibility();
            }
        });
        addPointerEventHandler(_settingButton);
    }

    private void InitializeBackButton() {
        addGameObject(_backButton = new BitmapButton(R.drawable.back, 0, 0));
        _backButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _showMenu = true;
                _showCard = false;
                _showGear = false;
                _showShop = false;
                _showMuseum = false;
                _showDig = false;
                _showSetting = false;
                _showFirstCard = false;
                _showSecondCard = false;
                _showThirdCard = false;
                setVisibility();
            }
        });
        addPointerEventHandler(_backButton);
    }

    private void InitializeExitButton() {
        addGameObject(_exitButton = new BitmapButton(R.drawable.exit, 500, 120));
        _exitButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _engine.exit();
            }
        });
        addPointerEventHandler(_exitButton);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    private void setVisibility() {
        boolean showOther = !_showDig && !_showSetting;

        SetButtonVisibility();

        _menuInfo.setVisible(_showMenu);
        _cardInfo.setVisible(_showCard);
        _gearInfo.setVisible(_showGear);
        _museumInfo.setVisible(_showMuseum);
        _shopInfo.setVisible(_showShop);
        _digInfo.setVisible(_showDig);
        _settingInfo.setVisible(_showSetting);

        _menuButton.setVisible(showOther);
        _cardButton.setVisible(showOther);
        _gearButton.setVisible(showOther);
        _museumButton.setVisible(showOther);
        _shopButton.setVisible(showOther);
        _playButton.setVisible(_showMenu);
        _changeCardsButton.setVisible(_pressFirstCard || _pressSecondCard || _pressThirdCard);
        _digButton.setVisible(_showDig);
        _settingButton.setVisible(showOther);
        _backButton.setVisible(_showSetting || _showDig);
        _exitButton.setVisible(_showSetting);
        _firstCard.GetBitmapButton().setVisible(_showFirstCard);
        _secondCard.GetBitmapButton().setVisible(_showSecondCard);
        _thirdCard.GetBitmapButton().setVisible(_showThirdCard);
    }

    private void SetButtonVisibility() {
        if (_showMenu)
            _menuButton.loadBitmap(R.drawable.menu_pressed);
        else
            _menuButton.loadBitmap(R.drawable.menu);

        if (_showCard)
            _cardButton.loadBitmap(R.drawable.card_pressed);
        else
            _cardButton.loadBitmap(R.drawable.card);

        if (_showGear)
            _gearButton.loadBitmap(R.drawable.gear_pressed);
        else
            _gearButton.loadBitmap(R.drawable.gear);

        if (_showMuseum)
            _museumButton.loadBitmap(R.drawable.museum_pressed);
        else
            _museumButton.loadBitmap(R.drawable.museum);

        if (_showShop)
            _shopButton.loadBitmap(R.drawable.shop_pressed);
        else
            _shopButton.loadBitmap(R.drawable.shop);

        if(_pressFirstCard)
            _firstCard.GetBitmapButton().loadBitmap(R.drawable.digit_0);
        else
            _firstCard.GetBitmapButton().loadBitmap(R.drawable.block0_invisible);

        if(_pressSecondCard)
            _secondCard.GetBitmapButton().loadBitmap(R.drawable.digit_0);
        else
            _secondCard.GetBitmapButton().loadBitmap(R.drawable.block1_unbreakable);

        if(_pressThirdCard)
            _thirdCard.GetBitmapButton().loadBitmap(R.drawable.digit_0);
        else
            _thirdCard.GetBitmapButton().loadBitmap(R.drawable.block2_dirt);
    }
}

