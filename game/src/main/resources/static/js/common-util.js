const commonUtil = {};

commonUtil.formToObject = function(dom) {
    const formData = new FormData(dom);
    const jsonObject = {};

    formData.forEach(function(value, key) {
        // 중복된 키 처리
        if (jsonObject.hasOwnProperty(key)) {
            if (!Array.isArray(jsonObject[key])) {
                jsonObject[key] = [jsonObject[key]];
            }
            jsonObject[key].push(value);
        } else {
            jsonObject[key] = value;
        }
    });

    return jsonObject;
}

commonUtil.ajaxRequest = function(method, url, data, successCallback, errorCallback) {
    const xhr = new XMLHttpRequest();

    // CSRF 토큰을 설정
    // config.html meta 에서 정의
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    xhr.open(method, url, true);
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');

    // CSRF 토큰 헤더 추가
    if (csrfToken && csrfHeader) {
        xhr.setRequestHeader(csrfHeader, csrfToken);
    }

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            try {
                const result = JSON.parse(xhr.responseText);

                if(result.success != null) {
                    if(!result.success) {
                        alert(result['errorMsg']);
                        errorCallback(xhr);

                        return;
                    }
                }

                successCallback(result);
            } catch (e) {
                errorCallback(xhr);
            }
        } else {
            errorCallback(xhr);
        }
    };

    xhr.onerror = function() {
        errorCallback(xhr.status, xhr.statusText);
    };

    if (data) {
        xhr.send(JSON.stringify(data));
    } else {
        xhr.send();
    }
};