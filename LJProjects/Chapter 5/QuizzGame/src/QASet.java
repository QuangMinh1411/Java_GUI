public class QASet {
    private int qaID;
    private final String question;
    private String[] answers = new String[5];
    private final String correctAnswer;

    public QASet(int qaID, String question, String[] answers, String correctAnswer) {
        this.qaID = qaID;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getQaID(){
        return qaID;
    }
    public String getQuestion(){
        return question;
    }
    public String[] getAnswers(){
        return answers;
    }
    public String getCorrectAnswer(){
        return correctAnswer;
    }
}
