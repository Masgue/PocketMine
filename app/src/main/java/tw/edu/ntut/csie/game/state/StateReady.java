package tw.edu.ntut.csie.game.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.card.CardAttributes;
import tw.edu.ntut.csie.game.card.character.DurabilityBonus;
import tw.edu.ntut.csie.game.card.mine.CoalBonus;
import tw.edu.ntut.csie.game.card.tool.BombBonus;
import tw.edu.ntut.csie.game.card.tool.DrillBonus;
import tw.edu.ntut.csie.game.card.tool.DynamiteBonus;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;
import tw.edu.ntut.csie.game.card.Card;
import tw.edu.ntut.csie.game.display.EnergyBar;

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

    private EnergyBar _level;
    private EnergyBar _energy;

    private Integer _money;
    private Integer _diamond;
    private Integer _durability;

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
        InitializeLevel();
        InitializeEnergy();
        InitializeMoney();
        InitializeDiamond();
        InitializeDurability();
        InitializeCards();
        InitializeMenuButton();
        initializeCardButton();
        initializeShopButton();
        initializeGearButton();
        initializeMuseumButton();
        initializePlayButton();
        InitializeCardsShufflingButton();
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

    private void InitializeLevel() {
        ArrayList<MovingBitmap> bar = new ArrayList<MovingBitmap>(){
            new MovingBitmap(R.drawable.block1_unbreakable);
        };
        _level = new EnergyBar(bar, 0, 300);
        addGameObject(_level.GetBar());
        addGameObject(_level.GetInteger());
        _level.GetBar().show();
        _level.GetInteger().show();
    }

    private void InitializeEnergy() {
        ArrayList<MovingBitmap> bar = new ArrayList<MovingBitmap>(){
            new MovingBitmap(R.drawable.block1_unbreakable);
        };
        _energy = new EnergyBar(bar, 0, 100);
        addGameObject(_energy.GetBar());
        addGameObject(_energy.GetInteger());
        _energy.GetBar().show();
        _energy.GetInteger().show();
    }

    private void InitializeMoney() {
        _money = new Integer(4, 1000, 0, 200);
        addGameObject(_money);
        _money.show();
    }

    private void InitializeDiamond() {
        _diamond = new Integer(3, 500, 0, 100);
        addGameObject(_diamond);
        _diamond.show();
    }

    private void InitializeDurability() {
        _durability = new Integer(3, 50, 300, 150);
        addGameObject(_durability);
        _durability.show();
    }

    private void InitializeCards() {
        LoadFirstCard(GenerateCard());
        LoadSecondCard(GenerateCard());
        LoadThirdCard(GenerateCard());
    }

    private void LoadFirstCard(Card card) {
        _firstCard = card;
        _firstCard.GetBitmapButton().setLocation(100, 253);
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

    private void LoadSecondCard(Card card) {
        _secondCard = card;
        _secondCard.GetBitmapButton().setLocation(100, 133);
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

    private void LoadThirdCard(Card card) {
        _thirdCard = card;
        _thirdCard.GetBitmapButton().setLocation(100, 13);
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

    private Card GenerateCard() {
        Card card;
        Random random = new Random(System.currentTimeMillis());
        int randomNumber = random.nextInt(5);

        if (randomNumber == 0)
            card = InitializeDurabilityBonus();
        else if (randomNumber == 1)
            card = InitializeCoalBonus();
        else if (randomNumber == 2)
            card = InitializeBombBonus();
        else if (randomNumber == 3)
            card = InitializeDrillBonus();
        else
            card = InitializeDynamiteBonus();

        return card;
    }

    private DurabilityBonus InitializeDurabilityBonus() {
        DurabilityBonus durabilityBonus = new DurabilityBonus();
        durabilityBonus.SetBitmapButton(new BitmapButton(R.drawable.durability_bonus));
        return durabilityBonus;
    }

    private CoalBonus InitializeCoalBonus() {
        CoalBonus coalBonus = new CoalBonus();
        coalBonus.SetBitmapButton(new BitmapButton(R.drawable.coal_bonus));
        return coalBonus;
    }

    private BombBonus InitializeBombBonus() {
        BombBonus bombBonus = new BombBonus();
        bombBonus.SetBitmapButton(new BitmapButton(R.drawable.bomb_bonus));
        return bombBonus;
    }

    private DrillBonus InitializeDrillBonus() {
        DrillBonus drillBonus = new DrillBonus();
        drillBonus.SetBitmapButton(new BitmapButton(R.drawable.drill_bonus));
        return drillBonus;
    }

    private DynamiteBonus InitializeDynamiteBonus() {
        DynamiteBonus dynamiteBonus = new DynamiteBonus();
        dynamiteBonus.SetBitmapButton(new BitmapButton(R.drawable.dynamite_bonus));
        return dynamiteBonus;
    }

    private void InitializeMenuButton() {
        addGameObject(_menuButton = new BitmapButton(R.drawable.menu, 65, 315));
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
        addGameObject(_cardButton = new BitmapButton(R.drawable.card, 65, 253));
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
        addGameObject(_gearButton = new BitmapButton(R.drawable.gear, 65, 191));
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
        addGameObject(_museumButton = new BitmapButton(R.drawable.museum, 65, 129));
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
        addGameObject(_shopButton = new BitmapButton(R.drawable.shop, 65, 67));
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

    private void InitializeCardsShufflingButton() {
        addGameObject(_changeCardsButton = new BitmapButton(R.drawable.shuffle, 400, 70));
        _changeCardsButton.addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                if (_pressFirstCard) {
                    removeGameObject(_firstCard.GetBitmapButton());
                    removePointerEventHandler(_firstCard.GetBitmapButton());
                    _firstCard.GetBitmapButton().removeButtonEventHandler(this);
                    LoadFirstCard(GenerateCard());
                    _pressFirstCard = false;
                }

                if (_pressSecondCard) {
                    removeGameObject(_secondCard.GetBitmapButton());
                    removePointerEventHandler(_secondCard.GetBitmapButton());
                    _secondCard.GetBitmapButton().removeButtonEventHandler(this);
                    LoadSecondCard(GenerateCard());
                    _pressSecondCard = false;
                }

                if (_pressThirdCard) {
                    removeGameObject(_thirdCard.GetBitmapButton());
                    removePointerEventHandler(_thirdCard.GetBitmapButton());
                    _thirdCard.GetBitmapButton().removeButtonEventHandler(this);
                    LoadThirdCard(GenerateCard());
                    _pressThirdCard = false;
                }

                setVisibility();
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
        addGameObject(_settingButton = new BitmapButton(R.drawable.setting_pressed, 65, 5));
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

        SetCardsVisibility();
    }

    private void SetCardsVisibility() {
        int type;

        type = _firstCard.GetCardType();
        if(_pressFirstCard)
            SetpressedCard(_firstCard, type);
        else
            SetUnpressedCard(_firstCard, type);

        type = _secondCard.GetCardType();
        if(_pressSecondCard)
            SetpressedCard(_secondCard, type);
        else
            SetUnpressedCard(_secondCard, type);

        type = _thirdCard.GetCardType();
        if(_pressThirdCard)
            SetpressedCard(_thirdCard, type);
        else
            SetUnpressedCard(_thirdCard, type);
    }

    private void SetpressedCard(Card card, int type) {
        if (type == 0) {
            card.GetBitmapButton().loadBitmap(R.drawable.durability_bonus_pressed);
        }
        else if (type == 1) {
            card.GetBitmapButton().loadBitmap(R.drawable.coal_bonus_pressed);
        }
        else if (type == 2) {
            card.GetBitmapButton().loadBitmap(R.drawable.bomb_bonus_pressed);
        }
        else if (type == 3) {
            card.GetBitmapButton().loadBitmap(R.drawable.drill_bonus_pressed);
        }
        else if (type == 4) {
            card.GetBitmapButton().loadBitmap(R.drawable.dynamite_bonus_pressed);
        }
    }

    private void SetUnpressedCard(Card card, int type) {
        if (type == 0) {
            card.GetBitmapButton().loadBitmap(R.drawable.durability_bonus);
        }
        else if (type == 1) {
            card.GetBitmapButton().loadBitmap(R.drawable.coal_bonus);
        }
        else if (type == 2) {
            card.GetBitmapButton().loadBitmap(R.drawable.bomb_bonus);
        }
        else if (type == 3) {
            card.GetBitmapButton().loadBitmap(R.drawable.drill_bonus);
        }
        else if (type == 4) {
            card.GetBitmapButton().loadBitmap(R.drawable.dynamite_bonus);
        }
    }
}
