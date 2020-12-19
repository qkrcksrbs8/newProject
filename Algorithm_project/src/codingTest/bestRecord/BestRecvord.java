package codingTest.bestRecord;
import java.util.ArrayList;
import java.util.Collections;

class Solution {

    class GenreInfo implements Comparable<GenreInfo> {
        //장르 이름
        String genreName;

        //총 재생수
        int sum;

        //장르
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

        //생성 및 업데이트
        for(int i=0; i<length; i++) {
            this.createOrUpdate(genres[i], plays[i], i);
        }

        //총 재생수로 정렬
        Collections.sort(genreInfos);

        //총 재생수 높은 순으로 2개까지 출력 
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

        //반환형에 맞게 변경
        int[] answer = Solution.convertIntegers(bestArray);
        return answer;
    }

    private void createOrUpdate(String inputGenreName, int inputPlay, int inputIdx)
    {
        //장르 인덱스
        int genreIdx = 0;

        //장르 존재여부 & 인덱스 
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

        //존재하지 않으면 초기화
        if(!exist)
        { 
            GenreInfo genreInfo = new GenreInfo();
            genreInfo.genreName = inputGenreName; 
            genreInfos.add(genreInfo);
        } 

        //값
        GenreInfo genreInfo = genreInfos.get(genreIdx);
        int sum = genreInfo.sum;
        int fir = genreInfo.plays[0];
        int firIdx = genreInfo.idxs[0];
        int sec = genreInfo.plays[1];
        int secIdx = genreInfo.idxs[1];

        //계산
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

        //갱신 
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