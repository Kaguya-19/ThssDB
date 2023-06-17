package cn.edu.thssdb.index;

import java.io.Serializable;
import java.util.ArrayList;

public class PageCounter implements Serializable {
  public ArrayList<Integer> indexList = new ArrayList<>();
  private static final long serialVersionUID = -5809782578123943999L;

  public int getMaxIndex() {
    return indexList.get(indexList.size() - 1);
  }

  public boolean updated = false;

  public int allocNewIndex() {
    updated = true;
    if (indexList.isEmpty()) {
      indexList.add(1);
      return 1;
    }
    int last = getMaxIndex() + 1;
    indexList.add(last);
    return last;
  }
}
