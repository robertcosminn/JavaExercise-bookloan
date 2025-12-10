package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.BookLoan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Implement the methods below so that the requirements are met.
 */
public class LibraryUtil {

    // private constructor to prevent instantiation
    private LibraryUtil() {
    }

    // load resource file from resources folder
    static List<String> loadResourceFile(final String fileName) {
        // Write your code here and replace the return statement

        List<String> lines;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("/loans/libraryLoans.csv")));
            lines = reader.lines().collect(Collectors.toList());
            return lines;
        } catch (Exception e) {
            System.out.println("File not found");
        }

        return Collections.emptyList();
    }

    // retrieve loans from csv lines
    // return a map of "valid" and "malformed" lines as keys and list of BookLoan objects as values
    protected static Map<String, List<BookLoan>> parseCsvLines(final List<String> file) {
        // Write your code here and replace the return statement

        Map<String, List<BookLoan>> result = new HashMap<>();
        List<BookLoan> valid = new ArrayList<>();
        List<BookLoan> malformed = new ArrayList<>();

        for (String line : file){
            if(line == null || line.isBlank())
                continue;

            //split
            String[] parts = line.split(",");

            //trim
            List<String> cleanFields = new ArrayList<>();
            for (String p : parts)
                cleanFields.add(p.trim());

            if (!cleanFields.isEmpty() && cleanFields.get(cleanFields.size() - 1).isEmpty())
                cleanFields.remove(cleanFields.size() - 1);

            //

            if(cleanFields.size() != 7){
                //
                String loanID = cleanFields.size() > 0 ? cleanFields.get(0) : null;
                String memberID = cleanFields.size() > 1 ? cleanFields.get(1) : null;
                String aux_loanDate = cleanFields.size() > 2 ? cleanFields.get(2) : null;
                String bookTitle = cleanFields.size() > 3 ? cleanFields.get(3) : null;
                String genre = cleanFields.size() > 4 ? cleanFields.get(4) : null;
                String author = cleanFields.size() > 5 ? cleanFields.get(5) : null;
                String aux_daysLoaned = cleanFields.size() > 6 ? cleanFields.get(6) : null;

                LocalDate loanDate = null;      //declar un obiect de tip LocalDate null ca sa pot sa stochez in el data din parsarea stringului auxLoanDate, daca nu merge il las null
                if(aux_loanDate != null && !aux_loanDate.isBlank()){
                    try{
                        loanDate = LocalDate.parse(aux_loanDate);
                    }catch (Exception e){
                        loanDate = null;
                    }
                }


                Integer daysLoaned = null;
                if(aux_daysLoaned != null && !aux_daysLoaned.isBlank()){
                    try{
                        daysLoaned = Integer.parseInt(aux_daysLoaned);
                    }catch (Exception e){
                        daysLoaned = null;
                    }
                }


                BookLoan malformedBookLoan = new BookLoan(loanID, memberID, loanDate, bookTitle, genre, author, daysLoaned);
                malformed.add(malformedBookLoan);
                continue;

            }else {

                String loanID = cleanFields.get(0);
                String memberID = cleanFields.get(1);
                String aux_loanDate = cleanFields.get(2);
                String bookTitle = cleanFields.get(3);
                String genre = cleanFields.get(4);
                String author = cleanFields.get(5);
                String aux_daysLoaned = cleanFields.get(6);


                LocalDate loanDate = null;
                if(aux_loanDate != null && !aux_loanDate.isBlank()) {
                try {
                    loanDate = LocalDate.parse(aux_loanDate);
                }catch (Exception e){
                    loanDate = null;
                    }
                }

                Integer daysLoaned = null;
                try {
                    daysLoaned = Integer.parseInt(aux_daysLoaned);
                }catch (Exception e){
                    daysLoaned = null;
                }

                boolean isValid = (loanDate != null && daysLoaned != null);

                BookLoan maybeValidLoan = new BookLoan(loanID, memberID, loanDate, bookTitle, genre, author, daysLoaned);

                if (isValid){
                    valid.add(maybeValidLoan);
                }else {
                    malformed.add(maybeValidLoan);
                }

            }
        }

        result.put("valid", valid);
        result.put("malformed", malformed);
        return result;
    }

    // count loans per genre
    // sorted alphabetically by genre
    protected static Map<String, Long> loansByGenre(final List<BookLoan> loans) {
        // Write your code here and replace the return statement

        Map<String, Long> countsPerGenre = loans.stream().collect(Collectors.groupingBy(BookLoan::getGenre, Collectors.counting()));

        //sortam cu TreeMap
        Map<String, Long> sorted = new TreeMap<>(countsPerGenre);

        return sorted;
    }

    // get top "n" authors by number of loans
    protected static List<String> topAuthorsByLoans(final List<BookLoan> loans, final int n) {

        Map<String, Long> countsPerAuthor = loans.stream().collect(Collectors.groupingBy(BookLoan::getAuthor, Collectors.counting()));

        List<String> topN = countsPerAuthor.entrySet().stream().sorted().limit(n).map(entry -> entry.getKey()).collect(Collectors.toList());

        return topN;
    }

    // get members who borrowed books from at least K genres
    protected static List<String> membersWithGenreDiversity(final List<BookLoan> loans, final int k) {
        // Write your code here and replace the return statement
        return Collections.emptyList();
    }

    // find the first book title containing a substring (case-insensitive)
    protected static Optional<BookLoan> findFirstBookContaining(final List<BookLoan> loans, final String book) {
        // Write your code here and replace the return statement
        return Optional.empty();
    }

    // checks if the book is present in the loans (case-insensitive)
    protected static Boolean isBookPresent(final List<BookLoan> loans, final String book) {
        // Write your code here and replace the return statement
        return null;
    }
}
