package FlashMindfunctions;

import java.time.LocalDate;

public class Card {

    static int nextId = 0;

        public int id;
        public String question;
        public String answer;
        public LocalDate buildDate;
        public int counter;
        public int correctCounter;
        public int falseCounter;
        public LocalDate lastLearn;

        public Card(){}
        public Card(String question, String answer, String dateString, int counter, int correctCounter, int falseCounter, String lastDate) {
            this.id = nextId++;
            this.question = question;
            this.answer = answer;
            this.buildDate = LocalDate.parse(dateString);
            this.counter = counter;
            this.correctCounter = correctCounter;
            this.falseCounter = falseCounter;
            this.lastLearn = LocalDate.parse(lastDate);
        }
    }

