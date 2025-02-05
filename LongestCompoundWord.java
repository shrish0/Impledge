import java.io.*;
import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
    }

    public boolean canFormWord(String word, Set<String> wordSet, int start, int count) {
        TrieNode node = root;
        for (int i = start; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
            if (node.isEndOfWord) {
                if (i == word.length() - 1) {
                    return count >= 1;
                }
                if (canFormWord(word, wordSet, i + 1, count + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}

public class LongestCompoundWord {
    public static List<String> readWordsFromFile(String filename) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        }
        return words;
    }

    public static String[] findLongestCompoundWords(List<String> words) {
        words.sort(Comparator.comparingInt(String::length).reversed());
        Set<String> wordSet = new HashSet<>(words);
        Trie trie = new Trie();
        
        for (String word : words) {
            trie.insert(word);
        }

        String longest = "";
        String secondLongest = "";
        
        for (String word : words) {
            wordSet.remove(word); 
            if (trie.canFormWord(word, wordSet, 0, 0)) {
                if (longest.isEmpty()) {
                    longest = word;
                } else if (secondLongest.isEmpty()) {
                    secondLongest = word;
                    break;
                }
            }
            wordSet.add(word);
        }

        return new String[]{longest, secondLongest};
    }

    public static void main(String[] args) {
        System.out.print("Enter File Name : ");
        Scanner sc = new Scanner(System.in);
        String inputFile = sc.next();
        try {
            List<String> words = readWordsFromFile(inputFile);
            long startTime = System.nanoTime();
            String[] result = findLongestCompoundWords(words);
            long elapsedTime = (System.nanoTime() - startTime) / 1_000_000; 
            
            System.out.println("Longest Compound Word: " + result[0]);
            System.out.println("Second Longest Compound Word: " + result[1]);
            System.out.println("Time taken to process file " + inputFile + ": " + elapsedTime + " milliseconds");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
