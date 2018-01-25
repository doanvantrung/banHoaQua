package application.data.model;

import java.util.List;

public class PaginableItemList<T>{ //clas phan trang
    private int pageSize;
    private  int pageNumber;
    private long totalProducts;
    // lay tong so prouducts chia cho page size thi se ra so trang
    private List<T> listData;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }
}
