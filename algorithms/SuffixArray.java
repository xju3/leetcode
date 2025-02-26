import java.util.Arrays;

public class SuffixArray {

    private String text;
    private int[] suffixArray;
    private int[] lcpArray;

    public SuffixArray(String text) {
        this.text = text;
        buildSuffixArray();
        buildLCPArray();
    }

    private void buildSuffixArray() {
        int n = text.length();
        suffixArray = new int[n];
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (i1, i2) -> text.substring(i1).compareTo(text.substring(i2)));
        for (int i = 0; i < n; i++) {
            suffixArray[i] = indices[i];
        }
    }

    private void buildLCPArray() {
        int n = text.length();
        lcpArray = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[suffixArray[i]] = i;
        }
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = suffixArray[rank[i] - 1];
                while (i + h < n && j + h < n && text.charAt(i + h) == text.charAt(j + h)) {
                    h++;
                }
                lcpArray[rank[i]] = h;
                if (h > 0) {
                    h--;
                }
            }
        }
    }

    public int[] getSuffixArray() {
        return suffixArray;
    }

    public int[] getLCPArray() {
        return lcpArray;
    }

    public static void main(String[] args) {
        String text = "banana$"; // $ is a unique sentinel character
        SuffixArray sa = new SuffixArray(text);
        System.out.println("Suffix Array: " + Arrays.toString(sa.getSuffixArray()));
        System.out.println("LCP Array: " + Arrays.toString(sa.getLCPArray()));
    }

    public String longestCommonSubstring(String s1, String s2) {
    String combined = s1 + "#" + s2;
    SuffixArray sa = new SuffixArray(combined);
    int[] lcp = sa.getLCPArray();
    int[] suffixArray = sa.getSuffixArray();
    int maxLCP = 0;
    int maxLCPIndex = -1;
    for (int i = 1; i < lcp.length; i++) {
        if (lcp[i] > maxLCP &&
            ((suffixArray[i] < s1.length() && suffixArray[i - 1] > s1.length()) ||
                (suffixArray[i] > s1.length() && suffixArray[i - 1] < s1.length()))) {
            maxLCP = lcp[i];
            maxLCPIndex = i;
        }
    }
    if (maxLCPIndex == -1) {
        return "";
    }
    return combined.substring(suffixArray[maxLCPIndex], suffixArray[maxLCPIndex] + maxLCP);
}


public String longestRepeatingSubstring(String text) {
        SuffixArray sa = new SuffixArray(text);
        int[] lcp = sa.getLCPArray();
        int[] suffixArray = sa.getSuffixArray();
        int maxLCP = 0;
        int maxLCPIndex = -1;
        for (int i = 1; i < lcp.length; i++) {
            if (lcp[i] > maxLCP) {
                maxLCP = lcp[i];
                maxLCPIndex = i;
            }
        }
        if (maxLCPIndex == -1) {
            return "";
        }
        return text.substring(suffixArray[maxLCPIndex], suffixArray[maxLCPIndex] + maxLCP);
    }

}
