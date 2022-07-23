public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> list = new LinkedListDeque<Character>();
        for(int i = 0; i < word.length(); i++){
            Character ch = word.charAt(i);
            list.addLast(ch);
        }
        return list;
    }

    public boolean isPalindrome(String word){
        if(word == null) return false;

        int len = word.length();
        for(int i = 0; i < len / 2; i++)
            if(word.charAt(i) != word.charAt(len-i-1))
                return false;
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word == null) return false;

        int len = word.length();
        for(int i = 0; i < len / 2; i++)
            if(!cc.equalChars(word.charAt(i), word.charAt(len-i-1)))
                return false;
        return true;
    }
}
