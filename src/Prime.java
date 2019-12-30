import java.util.Arrays;

public class Prime {
    public static void main(String[] args) {
       // isPrimeBasic(3);
        //ispimeBetterRootN(1077771);
        //System.out.println(seive(18));
       // System.out.println(furtherOptimisePrimeseive(17));
        System.out.println(segmentedSeive(25,36));
    }
    public static void isPrimeBasic( int n)
    {
        int i=1;
        for ( i = 2; i <n ; i++) {
            if(n%i==0)
            {
                System.out.println(" Not prime");
                break;
            }
        }
        if( i==n)
            System.out.println("prime");
    }
    public static void ispimeBetterRootN( int n)
    {// logic a*b=n (a,b are factors of n)
        // if a is a factor < n^1/2 then b is a factor > root n
        if(n==1)
            return;
        int i=0;
    boolean flag=true;
        for ( i = 2; i*i <=n ; i++) {
            if ( n% i==0)
            {
                System.out.println("NOT PRIME");
                flag=false;
                break;
            }
        }
        if( n==2|| flag==true)
            System.out.println("prime");
    }
    //Sieve of Eratosthenes
    //Given a number n, print all primes smaller than or equal to n.
    // It is also given that n is a small number. O(Nsqrt(n)) with above method
    // o( nloglogn)=> almost linear. with optimised seive
    public static boolean seive( int n)
    {
        boolean [] isprime= new boolean[n+1];
        Arrays.fill(isprime,true);//intiall all are prime
        isprime[0]=false;
        isprime[1]=false;
        for (int i = 2; i <=n ; i++) {
            if(isprime[i])
            {
                for( int j=2*i;j<=n;j+=i) //logic is all the multiples of primes
                    //are not prime
                    isprime[j]=false;

            }
        }
        return isprime[n];
    }
    public static boolean optimiseseive( int n)
    {//TIME COMPLEXITY ALMOST LINEAR O(nloglogn)
        //outer loop run n/2 times
        // inner loop runs => n/2+n/3+n/5+n/7+n/9 times
        //=> n( 1/2+1/3+1/5+1/7...);=loglogn=> very very small terms
        // for eg= if a number is increase 2^64 -max val of integer
        //  loglog2^64=log log 10^21(2^3~10) =log21.<2 which is O(n/2*2)=O(n)
        //so it is almost like a constant.
        boolean [] isprime= new boolean[n+1];
        for (int i = 0; i <=n ; i++) {
            if(i%2==0)
                isprime[i]=false;
                else
                    isprime[i]=true;
        }
        isprime[1]=false;//only two exceptions
        isprime[2]=true;//exception
        //optimisation 1 iterate only for odd numbers because only two is even prime number
        for (int i = 3; i <=n ; i=i+2) {
            if(isprime[i])
            {
                for( int j=i*i;j<=n;j+=i) //why i^2- we can take example of 25.
                    // because for value less then i^2 the values will already have been
                    //set to false by values less than i eg 25 , sqrt(25)=5;
                    //we start 5 from 5^2= 25 , 24-> made false/cut by 3s iteration
                    //22 - cut by
                    // to skip it => i^2,i^2+i,i^2+2*i.....
                    isprime[j]=false;

            }
        }
        return isprime[n];
    }
    public static boolean furtherOptimisePrimeseive( int n)
    {
        boolean [] isprime= new boolean[n+1];
        for (int i = 0; i <=n ; i++) {
            if(i%2==0)
                isprime[i]=false;
            else
                isprime[i]=true;
        }
        isprime[1]=false;//only two exceptions
        isprime[2]=true;//exception
        //optimisation 1 iterate only for odd numbers because only two is even prime number
        for (int i = 3; i <=n ; i=i+2) {
            if(isprime[i])
            {
                for( int j=i*i;j<=n;j+=2*i) //2i is optimisaion. why 2i?
                    // because i^2 is odd and i is odd so i^2+i => even
                    // but we have to skip even so i^2+2i = odd+even=odd

                    isprime[j]=false;

            }
        }
        return isprime[n];
    }
    //segmented sieve used when calculate primes in a range.
    //[a,b} => if a b very large we cant solve it as it will take lot of memory
    //a=10^10 b =10^10+ 10^5. not possible to make os big array
    //so we make two array of |b-a| size =10^5  and size b^(1/2).
    // so drastic reduction in size
    // 10^10(index 0)                       10^10+10^5 index(10^5)
    // [.....................................]
public static int segmentedSeive(int a, int b)
{int len1=(int)Math.sqrt(b)+1;
int len2=b-a+1;
int [] arr1= new int[len1];//1 prime , 0 not prime
int [] arr2= new int[len2];
Arrays.fill(arr2,1);
if( b<a || b==1 ||a<0)
    return -1;
arr1[2]=1;
    for (int i = 3; i <arr1.length ; i++) {
        if(i%2==0)
            arr1[i]=0;
        else
            arr1[i]=1;
    }
for( int i=3;i<len1;i=i+2)
{
    if( arr1[i]==1)
    {
        for (int j = i*i; j <len1 ; j+=2*i) {
            arr1[j]=0;
        }
    }
}
    for( int i=2;i<len1;i=i+1)
    {
        if( arr1[i]==1)
        {
            for (int j = a; j <=b ; j++) {
                if(j%i==0&&j!=i)// eg 3 is prime but it will be made non prime
                arr2[j-a]=0;
            }
        }
    }
    int ans=0;
    for (int i = 0; i <arr2.length ; i++) {
        if(arr2[i]==1)
            ans+=1;
    }
    for( int a1 : arr1)
    System.out.print(a1+"   ");
    System.out.println();
    for( int a1 : arr2)
        System.out.print(a1+"   ");
    System.out.println();
    return ans;
}


}
