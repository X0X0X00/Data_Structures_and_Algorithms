/** 
 * You must need to implement this interface for any credit. 
 * Please do not modify this file
 */

public interface Huffman {

/**
 *     Encodes the input file using Huffman Coding. Produces two files 
 *     
 *     @param inputFile The name of the input file to be encoded.
 *          Do not modify this file.  
 *
 *     @param outputFile The name of the output file  (after encoding)
 *                This would be a binary file.
 *                If the file already exists, overwrite it.   
 *
 *     @param freqFile  Stores the frequency of each byte 
 *          This file is a text file 
 *          where each row contains texual representation 
 *          of each byte and the  number of occurence of this byte
 *          separated by ':' 
 *          An example entry would look like:
 *          01100001:12345
 *          Which means 
 *          the letter a (ascii code 097, binary representation 01100001)
 *          has occureed 12345. This file does not need to be sorted. 
 *          If this file already exists, overwrite.   
 *                     */
   public void encode(String inputFile, String outputFile, String freqFile);



   
/**
 *     Decodes the input file (which is the output of encoding()) 
 *     using Huffman decoding.  
 *     
 *     @param inputFile The name of the input file to be decoded. 
 *     Do not modify this file. 
 *
 *     @param outputFile The name of the output file  (after decoding)
 *
 *     @param freqFile  freqFile produced after encoding. 
 *     Do not modify this file. 
 *                     */
   public void decode(String inputFile, String outputFile, String freqFile);
}
