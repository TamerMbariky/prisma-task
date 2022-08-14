package org.example.prismatask.dto;

import java.util.List;

public class SimilarResponse {
    private List<String> similar ;


    public SimilarResponse() {
    }

    public SimilarResponse(List<String> similar) {
        this.similar = similar;
    }


    public List<String> getSimilar() {
        return similar;
    }
}
