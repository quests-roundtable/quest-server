 package com.quest.questserver.test;

 import com.quest.questserver.model.Card.AllyCard;
 import com.quest.questserver.model.Card.AllyDecorator;
 import com.quest.questserver.model.Card.EventCard;
 import com.quest.questserver.model.Card.RankDecorator;
 import com.quest.questserver.model.Game;
 import com.quest.questserver.model.Player;
 import com.quest.questserver.model.Strategy.EventStrategy;
 import org.junit.jupiter.api.Test;

 import static org.junit.jupiter.api.Assertions.assertEquals;

 public class EventTest {

 @Test
 public void test() {

 // Initializing the game
 Game game = new Game(false);
 Player player1 = new Player("01", "player1");
 Player player2 = new Player("02", "player2");
 Player player3 = new Player("03", "player3");
 game.addPlayer(player1);
 game.addPlayer(player2);
 game.addPlayer(player3);

 game.start();

 // add virtual players
 player1.addShields(3);
 player2.addShields(4);
 player3.addShields(1);


 // Event_01 Player(s) with both lowest rank and least amount of shields,
 // receives 3 shields.
  EventStrategy strat1 = new EventStrategy(new EventCard("Event_01", "Chivalrous Deed",
          "Player(s) with both lowest rank and least amount of shields, receives 3 shields."));
  strat1.start(game);

 assertEquals(player3.getShields(),4);

 // Event_02 All Allies in play must be discarded.
 AllyCard ally1 = new AllyCard("Special_01", "Sir Gawain", 10, 0);
 RankDecorator rank = new AllyDecorator(player2.getRankCard(), ally1);
  EventStrategy strat2 = new EventStrategy(new EventCard("Event_02", "Court Called to Camelot",
          "All Allies in play must be discarded."));
  strat2.start(game);
 assertEquals(player2.getRankCard().getStrength(), 5);

 // Event_03 The highest ranked player(s) must place 1 weapon in the discard
 // pile. If unable to do so, 2 Foe Cards must be discard.

 // assertEquals(player1.getRankCard().getStrength(), 10);
 // ?

 // Event_04 The next player(s) to complete a Quest will receive 2 extra shields.

 // ?

 // Event_05 Drawer loses 2 shields if possible.
 EventStrategy strat5 = new EventStrategy(new EventCard("Event_05", "Plague",
         "Drawer loses 2 shields if possible."));
 strat5.start(game);
 assertEquals(player3.getShields(), 2);

 // Event_06 All players except the player drawing this card lose 1 Shield.
 EventStrategy strat6 = new EventStrategy(new EventCard("Event_06", "Pox",
         "All players except the player drawing this card lose 1 Shield."));
 strat6.start(game);
 assertEquals(player2.getShields(), 3);

 // Event_07 All players may immediately draw 2 Adventure Cards.
 player1.getPlayerHand().clear();
 EventStrategy strat7 = new EventStrategy(new EventCard("Event_07", "Prosperity Throughout the Realm",
         "All players may immediately draw 2 Adventure Cards."));
 strat7.start(game);
 assertEquals(player1.getPlayerHand().size(), 2);

 // Event_08 The lowest ranked player(s) immediately receives 2 Adventure Cards.
 player2.getPlayerHand().clear();
 player3.getPlayerHand().clear();

 EventStrategy strat8 = new EventStrategy(new EventCard("Event_08", "Queen's Favor",
         "The lowest ranked player(s) immediately receives 2 Adventure Cards."));
 strat8.start(game);

 assertEquals(player2.getPlayerHand().size(), 2);
 }
 }
