import java.util.*;
public class Main
{
    /*Implement a double hashing algorithm. Make the table size 16. Set the first hash function as:
    𝐻1 = 𝑘𝑒𝑦 𝑚𝑜𝑑 16; and the second hash function as: 𝐻2 = 2 (𝑘𝑒𝑦 𝑚𝑜𝑑 4) + 1. Recall: In
    double hashing, the slot is determined by (𝐻1 + 𝑖 𝐻2) 𝑚𝑜𝑑 16, in which 𝐻1 is the first hash
    function and 𝐻2 is the second hash function. Here 𝑖 multiplies 𝐻2, and initially 𝑖 is set to 0; then
if there is a collision we set 𝑖 to 1; then if there is still a collision we set 𝑖 to 2; and so on.
    Run your algorithm on example keys that you insert into the hash table. Try to illustrate the
    collisions and skips to new slots.
    Choose the programming language you like to implement the algorithm. Some options are Java,
    Javascript, C, C++, Python, Matlab, etc.
        Print: We ask you to print out the program state at each loop iteration (in this case, each
        insertion of a new key, and each collision that occurs). The program state in this case should be
    the key inserted, the array of numbers and the probe number “i”*/


    public static void main(String[] args)
    {
        //int[] exampleArray = fillArrayWithRandoms();
        //int[] exampleArray = {12, 8, 24, 56, 43, 54, 66};
        int[] exampleArray = {97, 14, 85, 40, 24, 61, 46};
        int arrSize = exampleArray.length;

        int[] arrayAsHashTable = new int[16];
        int H1 = 0;
        int H2 = 0;
        int i=0;
        int iProbe = 0;
        int key = 0;
        int newSlot = 0;

        for(int j=0; j<arrSize; j++)
        {
            /*1) calculate slot using H1 + 0*H2(key mod 16)
            * 2) if slot not empty, increase i and recalculate H1 + i*H2(key mod 16), key = exArr[j]
            * 3) if slot still not empty, increase i and recalculate (H1 + i*H2)(key mod 16)
            * 4) add key into slot: newSlot = (H1 + iH2) mod 16 ; arrAsHash[newSlot] = exArr[j]*/
            key = exampleArray[j];
            H1 = key%16;
            H2 = 2*(key%4) + 1;
            newSlot = (H1 + i*H2)%16;
            while (arrayAsHashTable[newSlot] != 0 /*&& arrayAsHashTable[newSlot] != key*/)
            {
                if(iProbe > arrayAsHashTable.length-1)
                {
                    iProbe = 0;
                    newSlot = (H1 + iProbe*H2)%16;
                    continue;
                }
                iProbe++;
                newSlot = (H1 + iProbe*H2)%16;
                //arrayAsHashTable[newSlot] = key;
            }
            iProbe = 0;
            arrayAsHashTable[newSlot] = key;
        }

        for(int ind=0; ind<arrayAsHashTable.length;ind++)
        {
            if(ind<arrayAsHashTable.length-1)
                System.out.print(arrayAsHashTable[ind]+", ");
            else
                System.out.println(arrayAsHashTable[ind]);
        }

    }

    public static int[] fillArrayWithRandoms()
    {
        int[] exampleArray = new int[7];
        int randInt;
        for(int i=0; i< exampleArray.length; i++)
        {
            randInt = (int)(Math.random() * ((100 - 0) + 1));
            exampleArray[i] = randInt;
        }
        return exampleArray;
    }
}
