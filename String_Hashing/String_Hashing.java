/*
 * akshaygupta26
 */

/**
  	Some values for P: 31, 53, 137, 257, 991, 99991, 999917. Or choose any from here: https://tinyurl.com/vhd3xvv
	Some values for MOD: 1e9 +7, +9, +21, +33, +87.
	
	ALERT: Make sure to modify "str[i]-'a'+1" to appropriate domain in case the domain is not lowercase.

 */
import java.io.*;
import java.util.*;
public class String_Hashing
{ 
	static class Hashing{
		int N;
		int base;
		int mod;
		int pw[];
		int inv[];
		int H[];
		
		Hashing(){
		}
		
		Hashing(int N,int mod,int base){
			this.N=N;
			this.mod=mod;
			this.base=base;
			pw=new int[N];
			inv=new int[N];
			H=new int[N];
		}
		
		int add(int a, int b, int mod){
		    int res = (a + b) % mod;
		    if(res < 0)
			res += mod;
		    return res;
    		}
    	 
		int mult(int a, int b, int mod){
		    int res = (int)((((long)a) * b) % mod);
		    if(res < 0)
		        res += mod;
		    return res;
		}
		
		int power(int a, int b, int mod){
		    int res = 1;
		    while(b>0){
		        if((b % 2) == 1)
		            res = mult(res, a, mod);
		        a = mult(a, a, mod);
		        b /= 2;
		    }
		    return res;
		}
		
		void precalc() {
		    pw[0] = 1;
		    for(int i = 1; i < N; i++)
		        pw[i] = mult(pw[i - 1], base, mod);
		    
		    int pw_inv = power(base , mod - 2 , mod);
		    inv[0] = 1;
		    for(int i = 1; i < N; i++)
		        inv[i] = mult(inv[i - 1], pw_inv, mod);
		}
		
		void build(String s){
		    int n = s.length();
		    for(int i = 0; i < n ; ++i){
		        H[i] = add((i == 0) ? 0 : H[i - 1], mult(pw[i], s.charAt(i) - 'a' + 1, mod), mod);
		    }
		}
		 
		int getHash(int x , int y){
		    int res = add(H[y], (x == 0) ? 0 : -H[x - 1], mod);
		    res = mult(res , (x == 0) ? 1 : inv[x], mod);
		    return res;
		}
	}
	
    public static void main(String[] args) 
	{ 
    	
//    		Single Hash starts
	    
		Hashing obj=new Hashing((int)1e6,(int)1e9+7,35);
		String s ="ABCDEFGABCDEFGH";

		obj.precalc();
		obj.build(s);
    	
		System.out.println(obj.getHash(2,5) == obj.getHash(9,12));
		
//		Single Hash Ends here
		
		
//		Double Hash Starts
	    
		Hashing h1=new Hashing((int)1e6,(int)1e9+7,35);
		Hashing h2=new Hashing((int)1e6,(int)1e9+9,32);
		
		h1.precalc();
		h1.build(s);
		h2.precalc();
		h2.build(s);
		
		
		
		System.out.println(h1.getHash(2,5) == h1.getHash(9,12) && h2.getHash(2,5) == h2.getHash(9,12));
		
//		Double Hash Ends here	
  
	} 
} 
