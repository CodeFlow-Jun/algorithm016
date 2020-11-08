package Week08;

public class Reverse_Bits_190 {
    public int reverseBits(int n) {
        int r = 0;
        for (int i = 0; i < 32 ; i++){
            r <<= 1;
            if ((n & 1) == 1){
                r++;
            }
            n >>= 1;
        }
        return r;
    }
}
