package ku.merseong.alcohollist;

public class Enums
{
    // 술의 종류
    public enum AlcCategory
    {
        OTHERS("기타"), SOJU("소주"), BEER("맥주"), CHEONGJU("청주"), TAKJU("탁주");

        final private String name;

        private AlcCategory(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }
    }

    // 느낀 감정의 척도
    public enum Feel
    {
        VERYBAD, BAD, SOSO, GOOD, VERYGOOD
    }
}
