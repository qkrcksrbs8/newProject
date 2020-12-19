package codingTest.bestRecord;
import java.util.ArrayList;
import java.util.Collections;

class Solution {

    class GenreInfo implements Comparable<GenreInfo> {
        //�帣 �̸�
        String genreName;

        //�� �����
        int sum;

        //�帣
        int[] plays = new int[2];
        int[] idxs = new int[2];    

        @Override
        public int compareTo(GenreInfo genreInfo) {
            if(this.sum < genreInfo.sum) {
                return 1;
            } else if(this.sum == genreInfo.sum){
                return 0;
            } else {
                return -1;
            }
        }
    }

    ArrayList<GenreInfo> genreInfos = new ArrayList<>(); 

    public int[] solution(String[] genres, int[] plays) {

        int length = genres.length;

        //���� �� ������Ʈ
        for(int i=0; i<length; i++) {
            this.createOrUpdate(genres[i], plays[i], i);
        }

        //�� ������� ����
        Collections.sort(genreInfos);

        //�� ����� ���� ������ 2������ ��� 
        int genreSize = genreInfos.size(); 
        ArrayList<Integer> bestArray = new ArrayList<>();
        for(int i=0; i<genreSize; i++)
        {
            GenreInfo genreInfo = genreInfos.get(i);

            //Idx 0
            bestArray.add(genreInfo.idxs[0]);

            //Idx 1
            if(genreInfo.plays[1] != 0)
            {
                bestArray.add(genreInfo.idxs[1]);
            }
        }

        //��ȯ���� �°� ����
        int[] answer = Solution.convertIntegers(bestArray);
        return answer;
    }

    private void createOrUpdate(String inputGenreName, int inputPlay, int inputIdx)
    {
        //�帣 �ε���
        int genreIdx = 0;

        //�帣 ���翩�� & �ε��� 
        boolean exist = false;
        for(GenreInfo genreInfo : genreInfos)
        {
            if(genreInfo.genreName.equals(inputGenreName))
            {
                exist = true;
                break;
            }

            genreIdx++;
        }

        //�������� ������ �ʱ�ȭ
        if(!exist)
        { 
            GenreInfo genreInfo = new GenreInfo();
            genreInfo.genreName = inputGenreName; 
            genreInfos.add(genreInfo);
        } 

        //��
        GenreInfo genreInfo = genreInfos.get(genreIdx);
        int sum = genreInfo.sum;
        int fir = genreInfo.plays[0];
        int firIdx = genreInfo.idxs[0];
        int sec = genreInfo.plays[1];
        int secIdx = genreInfo.idxs[1];

        //���
        sum += inputPlay;
        if(fir < inputPlay)
        {
            sec = fir;
            secIdx = firIdx;
            fir = inputPlay;
            firIdx = inputIdx;
        }
        else if(sec < inputPlay)
        {
            sec = inputPlay;
            secIdx = inputIdx;
        }

        //���� 
        genreInfo.sum = sum;
        genreInfo.plays[0] = fir;
        genreInfo.idxs[0] = firIdx;
        genreInfo.plays[1] = sec;
        genreInfo.idxs[1] = secIdx;
    }

    public static int[] convertIntegers(ArrayList<Integer> integers)
    {
        int size = integers.size();

        int[] ret = new int[size];
        for (int i=0; i <size; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}