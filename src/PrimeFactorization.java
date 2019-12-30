import java.lang.reflect.Array;
import java.util.ArrayList;

public class PrimeFactorization {
    public static void main(String[] args) {
        int n=100;// prime factors only 2 and 5
        System.out.println(primeFactorization(100));
        System.out.println(primeFactorization(71));
    }
    public static ArrayList<Integer> getAllPrimeFactorsTillN( int n)
    { if(n<2)
        return null;
        ArrayList< Integer > factors= new ArrayList<>();
        factors.add(2);
        //we see concept of sieve
        int[]arr= new int[n+1];
        for (int i = 3; i <=n ; i++) {
            if(i%2!=0)
                arr[i]=1;
        }
        arr[2]=1;
        for( int i=3;i<=n;i=i+2)
        {
            if( arr[i]==1)
            {
                for (int j = i*i; j <=n ; j+=2*i) {
                    arr[j]=0;
                }
            }
        }
        for(int i=3;i*i<=n;i++)
        {
            if(arr[i]==1)
            factors.add(i);
        }
        return factors;
    }
    public static ArrayList<Integer> primeFactorization(int n)
    {
        ArrayList<Integer> factors=getAllPrimeFactorsTillN(n);
        ArrayList<Integer> primeFactors=new ArrayList<>();
        for( int a: factors)
        {       if(a*a>n||n==1)
            break;//imp to remember
            if( n%a==0)
            {
                primeFactors.add(a);
                while(n%a==0)
                    n=n/a;//keep on dividing untill possible
            }
        }
        if(n!=1)//only when no is prime
            primeFactors.add(n);//prime has a factor (itself)
        return primeFactors;
}
}
