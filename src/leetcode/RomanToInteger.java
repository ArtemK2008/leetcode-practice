package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RomanToInteger {
  Map<Character, Integer> romanCharsValues;
  List<Character> validRomanValuesOrdered;

  public int romanToInt(String s) {
    initializeRomanData();
    if (!isValidInput(s)) {
      throw new IllegalArgumentException();
    }
    int result = 0;
    char[] usedRomanChars = s.toCharArray();
    if (usedRomanChars.length == 1) {
      return singleValidCharCase(s);
    }

    List<Integer> indexOfUsedCharsBySubstructing = new ArrayList<>();
    for (int i = 0; i < usedRomanChars.length - 1; i++) {
      Character current = usedRomanChars[i];
      Character next = usedRomanChars[i + 1];
      if (indexOfUsedCharsBySubstructing.contains(i)) {
        if (i == (usedRomanChars.length - 2)) {
          result += romanCharsValues.get(next);
        }
        continue;
      }
      if (validRomanValuesOrdered.indexOf(current) < validRomanValuesOrdered
          .indexOf(next)) {
        result += romanCharsValues.get(next) - romanCharsValues.get(current);
        indexOfUsedCharsBySubstructing.add(i + 1);
      } else {
        result += romanCharsValues.get(current);
        if (i == usedRomanChars.length - 2) {
          result += romanCharsValues.get(next);
        }
      }
    }
    return result;
  }

  private int singleValidCharCase(String s) {
    return romanCharsValues.get(s.charAt(0));
  }

  private boolean isValidInput(String s) {
    char[] usedRomanChars = s.toCharArray();
    boolean isValid = true;
    if ((usedRomanChars.length < 1) || (usedRomanChars.length > 15)) {
      return false;
    }
    for (char c : usedRomanChars) {
      if (!validRomanValuesOrdered.contains(c)) {
        return false;
      }
    }
    return isValid;
  }

  private void initializeRomanData() {
    romanCharsValues = new HashMap<>();
    validRomanValuesOrdered = new LinkedList<>();
    romanCharsValues.put('I', 1);
    validRomanValuesOrdered.add('I');
    romanCharsValues.put('V', 5);
    validRomanValuesOrdered.add('V');
    romanCharsValues.put('X', 10);
    validRomanValuesOrdered.add('X');
    romanCharsValues.put('L', 50);
    validRomanValuesOrdered.add('L');
    romanCharsValues.put('C', 100);
    validRomanValuesOrdered.add('C');
    romanCharsValues.put('D', 500);
    validRomanValuesOrdered.add('D');
    romanCharsValues.put('M', 1000);
    validRomanValuesOrdered.add('M');
  }
}
