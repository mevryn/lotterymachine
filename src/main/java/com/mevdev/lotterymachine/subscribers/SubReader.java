package com.mevdev.lotterymachine.subscribers;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public interface SubReader {

    List<Subscriber> getSubList() throws FileNotFoundException;
}
