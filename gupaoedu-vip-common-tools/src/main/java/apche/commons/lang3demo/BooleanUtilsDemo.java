package apche.commons.lang3demo;

import org.apache.commons.lang3.BooleanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooleanUtilsDemo {

    public static void main(String[] args) {
        System.out.println(BooleanUtils.toStringYesNo(false));
        System.out.println(BooleanUtils.toBooleanObject(2));
        System.out.println(BooleanUtils.toBooleanObject(0));
    }
}
