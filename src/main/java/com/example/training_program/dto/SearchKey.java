package com.example.training_program.dto;

import java.util.Objects;

public class SearchKey {
    private String groupName;
    private int page;
    private int size;

    public SearchKey(String groupName, int page, int size) {
        this.groupName = groupName;
        this.page = page;
        this.size = size;
    }

    // Это САМОЕ ВАЖНОЕ: чтобы HashMap понимала, что два ключа одинаковые
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchKey searchKey = (SearchKey) o;
        return page == searchKey.page &&
                size == searchKey.size &&
                Objects.equals(groupName, searchKey.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, page, size);
    }
}