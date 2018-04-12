package com.example.rct03.ebook_readerdm.netservices;

public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}