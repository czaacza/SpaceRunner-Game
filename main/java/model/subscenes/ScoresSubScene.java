package model.subscenes;

import javafx.geometry.Pos;
import javafx.scene.paint.Paint;
import model.InfoLabel;
import model.SpaceRunnerSubScene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ScoresSubScene extends SpaceRunnerSubScene {

    private InfoLabel titleLabel;
    private InfoLabel[] tableLabels;

    private String[] bestScores;

    public ScoresSubScene(int width, int height) {
        super(width, height);
        bestScores = new String[5];
        tableLabels = new InfoLabel[5];
        createTitleLabel();
    }

    private void createTitleLabel() {
        titleLabel = new InfoLabel("Scores");
        titleLabel.setLayoutX(110);
        titleLabel.setLayoutY(50);

        getPane().getChildren().add(titleLabel);
    }

    public void createScoresTable() {
        for (int i = 0; i < tableLabels.length; i++) {
            tableLabels[i] = new InfoLabel(i + 1 + ". " + bestScores[i], false);
            tableLabels[i].setLayoutX(210);
            tableLabels[i].setLayoutY(100 + i * 50);
            tableLabels[i].setPrefHeight(55);
            tableLabels[i].setPrefWidth(400);
            tableLabels[i].setLabelFont(28);
            tableLabels[i].setAlignment(Pos.CENTER_LEFT);
            if (i % 2 == 0) {
                tableLabels[i].setTextFill(Paint.valueOf("#fff"));
            }
        }
        getPane().getChildren().addAll(tableLabels);
    }

    public void loadBestScores() {
        File inputFile = new File("src/main/resources/data/scores.txt");
        try {
            FileReader fileReader = new FileReader(inputFile);
            Scanner scanner = new Scanner(inputFile);
            List<ScoreRecord> scoreRecords = new ArrayList<>();
            while(scanner.hasNextLine()){
                String currentLine = scanner.nextLine();
                String [] lineElements = currentLine.split(",");
                scoreRecords.add(new ScoreRecord(lineElements[0], Integer.parseInt(lineElements[1])));
            }
            Collections.sort(scoreRecords);
            for(int i = 0; i < bestScores.length; i++){
                try{
                    bestScores[i] = "" + scoreRecords.get(i).name + " : " + scoreRecords.get(i).score;
                } catch(IndexOutOfBoundsException e){
                    bestScores[i] = "";
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Couldn't open file " + inputFile.getName());
        }
    }

    private class ScoreRecord implements Comparable {
        private String name = "";
        private int score;

        public ScoreRecord(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Object o) {
                ScoreRecord scr = (ScoreRecord) o;
                return scr.score - this.score;
        }
    }


}
