package io;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

/**
 * Created by ericliu on 2/5/17.
 */
public class PlayFile {
    @Test
    public void listFiles() throws Exception {
        final File root = new File("/");
        final String[] list = root.list();
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
