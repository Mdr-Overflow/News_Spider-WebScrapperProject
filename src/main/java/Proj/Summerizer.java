package Proj;

class Summerizer {
    public static void summerize() {
        SummaryTool summary = new SummaryTool();
        summary.init();
        summary.extractSentenceFromContext();
        summary.groupSentencesIntoParagraphs();
        summary.printSentences();
        summary.createIntersectionMatrix();

        //System.out.println("INTERSECTION MATRIX");
        //summary.printIntersectionMatrix();

        summary.createDictionary();
        //summary.printDicationary();

        System.out.println("SUMMMARY");
        summary.createSummary();
        summary.printSummary();

        summary.printStats();
    }
}