public class LSDStringSort {

	// Sorts strings by least significant digit
	public void sort(String[] original, int digitIndex) {

		int R = 256; // extended ASCII alphabet size 
		int N = original.length;
		String[] sorted = new String[N];

		// Represents each pass through the array 
		for (int stringIndex = digitIndex-1; stringIndex >= 0; stringIndex--) {

			int[] count = new int[R+1];

			// count array: tallies up count of each letter
			// and makes running count 
			for (int i = 0; i < N; i++) {
				count[original[i].charAt(stringIndex)+1]++;
			}

			// count array: shift one down
			for (int r = 0; r < R; r++) {
				count[r+1] += count[r];
			}

			// count array: populate sorted array
			for (int i = 0; i < N; i++) {
				sorted[count[original[i].charAt(stringIndex)]++] = original[i];
			}

			// copy over sorted array into original array
			for (int i = 0; i < N; i++) {
				original[i] = sorted[i];
			}
		}
	}

	/// variable code

	// Finds longest length string in an array of strings
    public static int findLongestLength(String[] array) {

        int longest = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i].length() > longest) {
                longest = array[i].length();
            }
        }
        return longest;
    }

    // If string index is less than 0 or greater than length of string, 
    // add 0 to make every string the same length.
    // Otherwise return the character at string index
    public static int findCharAtInString(int arrayIndex, int stringIndex, String[] array) {

        if (stringIndex < 0 || stringIndex >= array[arrayIndex].length()) {
            return 0;
        }

        return array[arrayIndex].charAt(stringIndex);
    }

    // Rearranges the array of variable-length strings.
    public static String[] variableSort(String[] array) {

        int R = 256;    // extended ASCII alphabet size
        String[] sorted = new String[array.length];
        int digitIndex = findLongestLength(array);  // length of longest string serves as the "digit index"

        // Represents each pass through the array 
        for (int stringIndex = digitIndex-1; stringIndex >= 0; stringIndex--) {

            // Count array
            int[] count = new int[R + 1];

            // add 0's to make all strings same length
            // (pad shorter strings with null values)
            // and add tally to count array
            for (int i = 0; i < array.length; i++) {
                int c = findCharAtInString(i, stringIndex, array);
                count[c + 1]++;
            }

            // Count array: shift one down
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // Populate sorted array
            for (int i = 0; i < array.length; i++) {
                int c = findCharAtInString(i, stringIndex, array);
                sorted[count[c]++] = array[i];
            }

            // Copy over sorted array into original array
            for (int i = 0; i < array.length; i++) {
                array[i] = sorted[i];
            }
        }

        return array;
    }

    public static void main(String[] args) {

    	String[] testArray = {"now", "is", "the", "time", "for", "all", "good", "people", "to", "come", "to", "the", "aid", "of"};
    	String[] testSorted = variableSort(testArray);

    	// print results
    	for (int i = 0; i < testArray.length; i++) {
    		System.out.println(testSorted[i]);
    	}
    }
}
