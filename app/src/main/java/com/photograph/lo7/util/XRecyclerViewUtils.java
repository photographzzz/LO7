package com.photograph.lo7.util;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.photograph.lo7.AppHolder;
import com.photograph.lo7.adapter.ArticleAdapter;
import com.photograph.lo7.adapter.MomentAdapter;
import com.photograph.lo7.adapter.RoomAdapter;
import com.photograph.lo7.adapter.VideoAdapter;
import com.photograph.lo7.controller.ArticleController;
import com.photograph.lo7.controller.MomentController;
import com.photograph.lo7.controller.RoomController;
import com.photograph.lo7.controller.VideoController;
import com.photograph.lo7.controller.impl.StarArticleController;
import com.photograph.lo7.controller.impl.StarMomentController;
import com.photograph.lo7.controller.impl.StarVideoController;
import com.photograph.lo7.entity.Article;
import com.photograph.lo7.entity.Moment;
import com.photograph.lo7.entity.Video;
import com.photograph.lo7.httpsender.OnError;
import com.photograph.lo7.httpsender.Tip;
import com.photograph.lo7.view.SpaceItemDecoration;
import com.rxjava.rxlife.RxLife;

public class XRecyclerViewUtils {
    public static void initXRecyclerUtils(XRecyclerView recyclerView, Fragment fragment) {
        recyclerView.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        recyclerView.setLimitNumberToCallLoadMore(10);
        recyclerView.addItemDecoration(new SpaceItemDecoration(20));
        recyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
    }

    public static void loadMoreArticles(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        ArticleController.INSTANCE.getAllArticles(pageNum)
                .as(RxLife.asOnMain(fragment))
                .subscribe(articles -> {
                    if (articles.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        ArticleAdapter articleAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                articleAdapter = new ArticleAdapter(fragment.getContext(), articles);
                                recyclerView.setAdapter(articleAdapter);
                            } else {
                                articleAdapter = (ArticleAdapter) recyclerView.getAdapter();
                                articleAdapter.setArticles(articles);
                                articleAdapter.notifyDataSetChanged();
                            }
                        } else {
                            articleAdapter = (ArticleAdapter) recyclerView.getAdapter();
                            articleAdapter.loadMore(articles);
                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refreshArticles(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreArticles(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }

    public static void loadMoreMoments(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        MomentController.INSTANCE.getAllMoments(pageNum)
                .as(RxLife.asOnMain(fragment))
                .subscribe(moments -> {
                    if (moments.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        MomentAdapter momentAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                momentAdapter = new MomentAdapter(fragment.getContext(), moments);
                                recyclerView.setAdapter(momentAdapter);
                            } else {
                                momentAdapter = (MomentAdapter) recyclerView.getAdapter();
                                momentAdapter.setMoments(moments);
                                momentAdapter.notifyDataSetChanged();
                            }
                        } else {
                            momentAdapter = (MomentAdapter) recyclerView.getAdapter();
                            momentAdapter.loadMore(moments);

                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refreshMoments(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreMoments(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }

    public static void loadMoreVideos(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        VideoController.INSTANCE.getAllVideos(pageNum)
                .as(RxLife.asOnMain(fragment))
                .subscribe(videos -> {
                    if (videos.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        VideoAdapter videoAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                videoAdapter = new VideoAdapter(fragment.getContext(), videos);
                                recyclerView.setAdapter(videoAdapter);
                            } else {
                                videoAdapter = (VideoAdapter) recyclerView.getAdapter();
                                videoAdapter.setVideos(videos);
                                videoAdapter.notifyDataSetChanged();
                            }
                        } else {
                            videoAdapter = (VideoAdapter) recyclerView.getAdapter();
                            videoAdapter.loadMore(videos);

                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refreshVideos(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreVideos(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }


    public static void loadMoreMyArticles(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        ArticleController.INSTANCE.getArticlesByAuthorId(pageNum)
                .as(RxLife.asOnMain(fragment))
                .subscribe(articles -> {
                    if (articles.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        ArticleAdapter articleAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                articleAdapter = new ArticleAdapter(fragment.getContext(), articles);
                                recyclerView.setAdapter(articleAdapter);
                            } else {
                                articleAdapter = (ArticleAdapter) recyclerView.getAdapter();
                                articleAdapter.setArticles(articles);
                                articleAdapter.notifyDataSetChanged();
                            }
                        } else {
                            articleAdapter = (ArticleAdapter) recyclerView.getAdapter();
                            articleAdapter.loadMore(articles);
                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refreshMyArticles(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreMyArticles(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }

    public static void loadMoreMyMoments(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        MomentController.INSTANCE.getMomentsByAuthorId(pageNum)
                .as(RxLife.asOnMain(fragment))
                .subscribe(moments -> {
                    if (moments.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        MomentAdapter momentAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                momentAdapter = new MomentAdapter(fragment.getContext(), moments);
                                recyclerView.setAdapter(momentAdapter);
                            } else {
                                momentAdapter = (MomentAdapter) recyclerView.getAdapter();
                                momentAdapter.setMoments(moments);
                                momentAdapter.notifyDataSetChanged();
                            }
                        } else {
                            momentAdapter = (MomentAdapter) recyclerView.getAdapter();
                            momentAdapter.loadMore(moments);
                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refreshMyMoments(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreMyMoments(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }

    public static void loadMoreStarArticles(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        StarArticleController.INSTANCE.getAllStarVisitableOfUser(AppHolder.currentUser.getId(), Article.class)
                .as(RxLife.asOnMain(fragment))
                .subscribe(articles -> {
                    if (articles.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        ArticleAdapter articleAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                articleAdapter = new ArticleAdapter(fragment.getContext(), articles);
                                recyclerView.setAdapter(articleAdapter);
                            } else {
                                articleAdapter = (ArticleAdapter) recyclerView.getAdapter();
                                articleAdapter.setArticles(articles);
                                articleAdapter.notifyDataSetChanged();
                            }
                        } else {
                            articleAdapter = (ArticleAdapter) recyclerView.getAdapter();
                            articleAdapter.loadMore(articles);
                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refreshStarArticles(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreStarArticles(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }

    public static void loadMoreStarMoments(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        StarMomentController.INSTANCE.getAllStarVisitableOfUser(AppHolder.currentUser.getId(), Moment.class)
                .as(RxLife.asOnMain(fragment))
                .subscribe(moments -> {
                    if (moments.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        MomentAdapter momentAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                momentAdapter = new MomentAdapter(fragment.getContext(), moments);
                                recyclerView.setAdapter(momentAdapter);
                            } else {
                                momentAdapter = (MomentAdapter) recyclerView.getAdapter();
                                momentAdapter.setMoments(moments);
                                momentAdapter.notifyDataSetChanged();
                            }
                        } else {
                            momentAdapter = (MomentAdapter) recyclerView.getAdapter();
                            momentAdapter.loadMore(moments);
                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refreshStarMoments(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreStarMoments(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }


    public static void loadMoreStarVideos(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        StarVideoController.INSTANCE.getAllStarVisitableOfUser(AppHolder.currentUser.getId(), Video.class)
                .as(RxLife.asOnMain(fragment))
                .subscribe(videos -> {
                    if (videos.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        VideoAdapter videoAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                videoAdapter = new VideoAdapter(fragment.getContext(), videos);
                                recyclerView.setAdapter(videoAdapter);
                            } else {
                                videoAdapter = (VideoAdapter) recyclerView.getAdapter();
                                videoAdapter.setVideos(videos);
                                videoAdapter.notifyDataSetChanged();
                            }
                        } else {
                            videoAdapter = (VideoAdapter) recyclerView.getAdapter();
                            videoAdapter.loadMore(videos);
                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refreshStarVideos(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreStarVideos(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }


    public static void loadMoreFreeRooms(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        RoomController.INSTANCE.findFreeRoom()
                .as(RxLife.asOnMain(fragment))
                .subscribe(rooms -> {
                    if (rooms.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        RoomAdapter roomAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                roomAdapter = new RoomAdapter(fragment.getContext(), rooms, true);
                                recyclerView.setAdapter(roomAdapter);
                            } else {
                                roomAdapter = (RoomAdapter) recyclerView.getAdapter();
                                roomAdapter.setRooms(rooms);
                                roomAdapter.notifyDataSetChanged();
                            }
                        } else {
                            roomAdapter = (RoomAdapter) recyclerView.getAdapter();
                            roomAdapter.loadMore(rooms);
                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refreshFreeRooms(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreFreeRooms(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }

    public static void loadMoreMyRooms(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        RoomController.INSTANCE.findMyRoom()
                .as(RxLife.asOnMain(fragment))
                .subscribe(rooms -> {
                    if (rooms.size() == 0) {
                        Tip.show("已经到最后了");
                    } else {
                        RoomAdapter roomAdapter;
                        if (pageNum == 1) {
                            if (recyclerView.getAdapter() == null) {
                                roomAdapter = new RoomAdapter(fragment.getContext(), rooms, false);
                                recyclerView.setAdapter(roomAdapter);
                            } else {
                                roomAdapter = (RoomAdapter) recyclerView.getAdapter();
                                roomAdapter.setRooms(rooms);
                                roomAdapter.notifyDataSetChanged();
                            }
                        } else {
                            roomAdapter = (RoomAdapter) recyclerView.getAdapter();
                            roomAdapter.loadMore(rooms);
                        }
                    }
                }, (OnError) error -> error.show(error.getErrorMsg()));
    }

    public static void refreshMyRooms(XRecyclerView recyclerView, int pageNum, Fragment fragment) {
        loadMoreMyRooms(recyclerView, pageNum, fragment);
        recyclerView.refreshComplete();
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
    }

}
