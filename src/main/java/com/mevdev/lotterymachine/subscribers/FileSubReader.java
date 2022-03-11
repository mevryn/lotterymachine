package com.mevdev.lotterymachine.subscribers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class FileSubReader implements SubReader, EventListener {
    @Override
    public List<Subscriber> getSubList() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        if (Objects.nonNull(file)) {
            List<List<String>> subs = readFile(file);
            return parseIntoSubscribers(subs);
        } else {
            return List.of();
        }
    }


    private List<Subscriber> parseIntoSubscribers(List<List<String>> listOfSubs) {
        List<Subscriber> subscribers = new ArrayList<>();
        for (int i = 0; i < listOfSubs.size() - 1; i++) {
            subscribers.add(parseSubscriber(listOfSubs.get(i + 1)));
        }
        return subscribers;
    }

    private Subscriber parseSubscriber(List<String> aSubString) {
        return new Subscriber(aSubString.get(Subscriber.getNameIndexInCSV()),
                Tier.getTier(aSubString.get(Subscriber.getTierIndexInCSV())),
                SubType.returnSubType(aSubString.get(Subscriber.getTypeIndexInCSV())));
    }

    private List<List<String>> readFile(File file) throws FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return records;
    }
}
