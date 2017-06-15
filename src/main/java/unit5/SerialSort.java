package unit5;

public class SerialSort {
  public static void pOddEvensort(int[] arr) {
    // ∆Ê≈ºΩªªª≈≈–Ú£¨¥Æ––¥˙¬Î
    int exchFlag = 1, start = 0;
    while (exchFlag == 1 || start == 1) {
      exchFlag = 0;
      for (int i = start; i < arr.length - 1; i += 2) {
        if (arr[i] > arr[i + 1]) {
          int temp = arr[i];
          arr[i] = arr[i + 1];
          arr[i + 1] = temp;
          exchFlag = 1;
        }
      }
      if (start == 0) {
        start = 1;
      } else {
        start = 0;
      }
    }
  }

  public static void insertSort(int[] arr) {
    int length = arr.length;
    int j, i, key;
    for (i = 1; i < length; i++) {
      key = arr[i];
      j = i - 1;
      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = key;
    }
  }

  public static void shellSort(int[] arr) {
    int h = 1;
    while (h <= arr.length / 3) {
      h = h * 3 + 1;
    }
    while (h > 0) {
      for (int i = h; i < arr.length; i++) {
        if (arr[i] < arr[i - h]) {
          int tmp = arr[i];
          int j = i - h;
          while (j >= 0 && arr[j] > tmp) {
            arr[j + h] = arr[j];
            j -= h;
          }
          arr[j + h] = tmp;
        }
      }
      h = (h - 1) / 3;
    }
  }
}
