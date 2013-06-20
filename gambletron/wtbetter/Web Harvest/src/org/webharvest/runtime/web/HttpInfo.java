/*  Copyright (c) 2006-2007, Vladimir Nikic
    All rights reserved.

    Redistribution and use of this software in source and binary forms,
    with or without modification, are permitted provided that the following
    conditions are met:

    * Redistributions of source code must retain the above
      copyright notice, this list of conditions and the
      following disclaimer.

    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the
      following disclaimer in the documentation and/or other
      materials provided with the distribution.

    * The name of Web-Harvest may not be used to endorse or promote
      products derived from this software without specific prior
      written permission.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
    ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
    LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
    CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
    SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
    INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
    CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
    POSSIBILITY OF SUCH DAMAGE.

    You can contact Vladimir Nikic by sending e-mail to
    nikic_vladimir@yahoo.com. Please include the word "Web-Harvest" in the
    subject line.
*/

package org.webharvest.runtime.web;

import org.apache.commons.httpclient.HttpClient;

import java.util.Map;
import java.util.HashMap;

/**
 * Class offers access to HTTP client and response details to the user. 
 */
public class HttpInfo {

    public HttpClient client;
    
    public long contentLength = 0;
    public String charset = "";
    public String mimeType = "";
    public Map headers = new HashMap(); 
    public int statusCode = 0;
    public String statusText = "";

    public long totalLength = 0;
    public int totalResponses = 0;

    public HttpInfo(HttpClient client) {
        this.client = client;
    }

    public void setResponse(HttpResponseWrapper wrapper) {
        this.contentLength = wrapper.getContentLength();
        this.charset = wrapper.getCharset();
        this.mimeType = wrapper.getMimeType(); 
        this.headers = wrapper.getHeaders(); 
        this.statusCode = wrapper.getStatusCode();
        this.statusText = wrapper.getStatusText();

        this.totalLength += this.contentLength;
        this.totalResponses++;
    }

}