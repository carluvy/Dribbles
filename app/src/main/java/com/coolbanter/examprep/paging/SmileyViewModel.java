package com.coolbanter.examprep.paging;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.coolbanter.examprep.data.DataRepository;
import com.coolbanter.examprep.data.Smiley;


/**
 * Store and manage data for SmileyListActivity.
 */
public class SmileyViewModel extends ViewModel {

    private final DataRepository mRepository;
    public static int PAGE_SIZE = 30;
    public static boolean PLACEHOLDERS = true;
    public MutableLiveData<Smiley> _liveSmiley;
    public LiveData<PagedList<Smiley>> liveSmiley;
    public LiveData<PagedList<Smiley>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Smiley>> liveDataSource;


    public SmileyViewModel(DataRepository repository) {
        this.mRepository = repository;
        this.liveSmiley = new MutableLiveData<>();




//        _liveSmiley = new LivePagedListBuilder<>(mRepository.getRandomSmileys(30), PAGE_SIZE).build();

//        liveSmiley = Transformations.switchMap(mRepository.getRandomSmileys(6), input -> liveSmiley);
//
        PagedList.Config config = (new PagedList.Config.Builder())
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(PLACEHOLDERS)
                .build();

        itemPagedList = new LivePagedListBuilder<>(mRepository.getSmileys(), config)
                .build();

//        liveSmiley = new LivePagedListBuilder<>(mRepository.getSmileys(), config).build();


//        liveSmiley = new LivePagedListBuilder<>(mRepository.getSmiley(), config)
//                .build();

//        _liveSmiley = Transformations.switchMap(liveSmiley, input -> {
//            if(input == null || input.equals("") || input.equals("%%")) {
//                return new LivePagedListBuilder<>(mRepository.getSmiley(), config).build();
//
//            }
//            return liveSmiley;
//        });
    }

    public void save(Smiley smiley) {
        mRepository.insert(smiley);
    }

    public void delete(Smiley smiley) {
        mRepository.delete(smiley);
    }

//    public void getSmiley() {mRepository.getRandomSmileys(PAGE_SIZE);}







//public LiveData<PagedList<Smiley>> smileys =
//        Transformations.switchMap(liveSmiley, new Function<Smiley, LiveData<PagedList<Smiley>>>() {
//            @Override
//            public LiveData<PagedList<Smiley>> apply(Smiley input) {
//                new LivePagedListBuilder<>(mRepository.getRandomSmileys(PAGE_SIZE), )
//                        .build();
//                mRepository.getRandomSmileys(PAGE_SIZE);
//
//                return smileys;
//            }
//
//        });

//    public void get(Smiley smiley) {mRepository.getSmiley();}


}
