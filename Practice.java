public class Practice {

    public int findPattern(String input, String pattern){
        int count = 0;
        int error = 0;
        int currIdx = -1;
        int permIdx = -1;
        int count_new = 0;
        for(int i = 0; i < input.length(); ){
            if(input.charAt(i) == pattern.charAt(count)){
                if(count == 0){
                    currIdx = i;
                }
                count ++;
                if(count == pattern.length()){
                    if(error == 1){
                        permIdx = currIdx;
                        count = 0;
                        error = 0;
                    }
                    else{
                        return currIdx;
                    }
                }
                i++;
            }
            else{
                error ++;
                count ++;
                if(error >= 2){
                    count = 0;
                    error = 0;
                    if(input.charAt(i-1) == pattern.charAt(0)){
                        i = i -1;
                    }
                }
                else{
                    i ++;
                }
            }
        }

       return permIdx;
    }

    public static void main(String[] args) {
        Practice test = new Practice();
        System.out.println(test.findPattern("BBAABBCCDEF", "ABCDEF"));

    }
}
