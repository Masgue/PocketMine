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
import tw.edu.ntut.csie.game.display.BuyableButton;
import tw.edu.ntut.csie.game.display.Energy;
import tw.edu.ntut.csie.game.display.Level;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;
import tw.edu.ntut.csie.game.card.Card;
import tw.edu.ntut.csie.game.extend.Integer;

public class StateReady extends AbstractGameState {
    private MovingBitmap _menuInfo;
    private MovingBitmap _cardInfo;
    private MovingBitmap _gearInfo;
    private MovingBitmap _shopInfo;
    private MovingBitmap _museumInfo;
    private MovingBitmap _digInfo;
    private MovingBitmap _settingInfo;
    private MovingBitmap _about;

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

    private Level _level;
    private Energy _energy;

    private Integer _money;
    private Integer _diamond;

    private BuyableButton _durabilityPurchase;
    private BuyableButton _moneyPurchase;
    private BuyableButton _diamondPurchase;
    private BuyableButton _levelPurchase;
    private BuyableButton _energyPurchase;

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
        addGameObject(_menuInfo = new MovingBitmap(R.drawable.state_ready_background));
        addGameObject(_cardInfo = new MovingBitmap(R.drawable.state_ready_background));
        addGameObject(_gearInfo = new MovingBitmap(R.drawable.state_ready_background));
        addGameObject(_shopInfo = new MovingBitmap(R.drawable.state_ready_background));
        addGameObject(_museumInfo = new MovingBitmap(R.drawable.state_ready_background));
        addGameObject(_digInfo = new MovingBitmap(R.drawable.state_ready_background));
        addGameObject(_settingInfo = new MovingBitmap(R.drawable.state_ready_background));
        addGameObject(_about = new MovingBitmap(R.drawable.about));
        _about.setLocation(150, 8);
        if (data != null) {
            InitializeEnergy((int)data.get("Energy") - 1);
            InitializeLevel((int)data.get("Level"));
            InitializeMoney((int)data.get("Money"));
        }
        else {
            InitializeEnergy(5);
            InitializeLevel(0);
            InitializeMoney(1000);
        }
        InitializeDiamond();
        InitializeDurabilityPurchaseButton();
        InitializeMoneyPurchaseButton();
        InitializeDiamondPurchaseButton();
        InitializeLevelPurchaseButton();
        InitializeEnergyPurchaseButton();
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
        if (data != null) {
            _money.add((int)data.get("Score"));
            _level.Add((int)data.get("Experience"));
        }
        setVisibility();
    }

    private void InitializeLevel(int experience) {
        _level = new Level(experience, 10, 270);
        addGameObject(_level.GetBar());
        addGameObject(_level.GetInteger());
    }

    private void InitializeEnergy(int energy) {
        _energy = new Energy(energy, 10, 10);
        _energy.SetMovingBitmap();
        addGameObject(_energy.GetBar());
        addGameObject(_energy.GetInteger());
    }

    private void InitializeMoney(int money) {
        _money = new Integer(4, money, 10, 140);
        addGameObject(_money);
    }

    private void InitializeDiamond() {
        _diamond = new Integer(3, 500, 10, 75);
        addGameObject(_diamond);
    }

    private void InitializeDurabilityPurchaseButton() {
        _durabilityPurchase = new BuyableButton(new BitmapButton(R.drawable.purchase), new MovingBitmap(R.drawable.pickaxe), 50, 300, 60);

        addGameObject(_durabilityPurchase.GetMovingBitmap());
        addGameObject(_durabilityPurchase.GetBitmapButton());
        _durabilityPurchase.GetBitmapButton().addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _money.add(-10);
                _durabilityPurchase.GetInteger().add(10);
                setVisibility();
            }
        });
        addPointerEventHandler(_durabilityPurchase.GetBitmapButton());
        addGameObject(_durabilityPurchase.GetInteger());
    }

    private void InitializeMoneyPurchaseButton() {
        _moneyPurchase = new BuyableButton(new BitmapButton(R.drawable.purchase), new MovingBitmap(R.drawable.money), 25, 200, 60);

        addGameObject(_moneyPurchase.GetMovingBitmap());
        addGameObject(_moneyPurchase.GetBitmapButton());
        _moneyPurchase.GetBitmapButton().addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _money.add(25);
                setVisibility();
            }
        });
        addPointerEventHandler(_moneyPurchase.GetBitmapButton());
        addGameObject(_moneyPurchase.GetInteger());
    }

    private void InitializeDiamondPurchaseButton() {
        _diamondPurchase = new BuyableButton(new BitmapButton(R.drawable.purchase), new MovingBitmap(R.drawable.block7_diamond), 5, 300, 60);

        addGameObject(_diamondPurchase.GetMovingBitmap());
        addGameObject(_diamondPurchase.GetBitmapButton());
        _diamondPurchase.GetBitmapButton().addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _diamond.add(5);
                setVisibility();
            }
        });
        addPointerEventHandler(_diamondPurchase.GetBitmapButton());
        addGameObject(_diamondPurchase.GetInteger());
    }

    private void InitializeLevelPurchaseButton() {
        _levelPurchase = new BuyableButton(new BitmapButton(R.drawable.purchase), new MovingBitmap(R.drawable.experience), 1, 400, 60);

        addGameObject(_levelPurchase.GetMovingBitmap());
        addGameObject(_levelPurchase.GetBitmapButton());
        _levelPurchase.GetBitmapButton().addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                _level.Add(1);
                setVisibility();
            }
        });
        addPointerEventHandler(_levelPurchase.GetBitmapButton());
        addGameObject(_levelPurchase.GetInteger());
    }

    private void InitializeEnergyPurchaseButton() {
        _energyPurchase = new BuyableButton(new BitmapButton(R.drawable.purchase), new MovingBitmap(R.drawable.energy), 1, 500, 60);

        addGameObject(_energyPurchase.GetMovingBitmap());
        addGameObject(_energyPurchase.GetBitmapButton());
        _energyPurchase.GetBitmapButton().addButtonEventHandler(new ButtonEventHandler() {
            @Override
            public void perform(BitmapButton button) {
                if (_energy.GetInteger().getValue() < 10) {
                    _energy.GetInteger().add(1);
                    _energy.SetMovingBitmap();
                }
                setVisibility();
            }
        });
        addPointerEventHandler(_energyPurchase.GetBitmapButton());
        addGameObject(_energyPurchase.GetInteger());
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
        ArrayList<CardAttributes> cardAttributes = new ArrayList<CardAttributes>();
        Map<String, Object> map = new HashMap<String, Object>();
        int durability = _durabilityPurchase.GetInteger().getValue();

        cardAttributes.add(_firstCard.GetCardAttributes());
        cardAttributes.add(_secondCard.GetCardAttributes());
        cardAttributes.add(_thirdCard.GetCardAttributes());
        map.put("CardAttributes", cardAttributes);
        map.put("Durability", durability);
        map.put("Score", _money.getValue());
        map.put("Experience", _level.GetCurrentExperience());
        map.put("Energy", _energy.GetInteger().getValue());
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
        _about.setVisible(_showSetting);

        _level.GetBar().setVisible(showOther);
        _level.GetInteger().setVisible(showOther);
        _energy.GetBar().setVisible(showOther);
        _energy.GetInteger().setVisible(showOther);

        _money.setVisible(showOther);
        _diamond.setVisible(showOther);

        _durabilityPurchase.GetInteger().setVisible(_showMenu);
        _durabilityPurchase.GetBitmapButton().setVisible(_showMenu);
        _durabilityPurchase.GetMovingBitmap().setVisible(_showMenu);
        _moneyPurchase.GetInteger().setVisible(_showShop);
        _moneyPurchase.GetBitmapButton().setVisible(_showShop);
        _moneyPurchase.GetMovingBitmap().setVisible(_showShop);
        _diamondPurchase.GetInteger().setVisible(_showShop);
        _diamondPurchase.GetBitmapButton().setVisible(_showShop);
        _diamondPurchase.GetMovingBitmap().setVisible(_showShop);
        _levelPurchase.GetInteger().setVisible(_showShop);
        _levelPurchase.GetBitmapButton().setVisible(_showShop);
        _levelPurchase.GetMovingBitmap().setVisible(_showShop);
        _energyPurchase.GetInteger().setVisible(_showShop);
        _energyPurchase.GetBitmapButton().setVisible(_showShop);
        _energyPurchase.GetMovingBitmap().setVisible(_showShop);

        _menuButton.setVisible(showOther);
        _cardButton.setVisible(showOther);
        _gearButton.setVisible(showOther);
        _museumButton.setVisible(showOther);
        _shopButton.setVisible(showOther);
        _playButton.setVisible(_showMenu);
        _changeCardsButton.setVisible(_showDig && (_pressFirstCard || _pressSecondCard || _pressThirdCard));
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
