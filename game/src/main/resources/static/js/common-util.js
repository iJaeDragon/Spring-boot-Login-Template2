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

    xhr.open(method, url, true);
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {
                // 요청이 성공했을 때
                const result = JSON.parse(xhr.response)

                successCallback(result);
            } else {
                // 요청이 실패했을 때
                errorCallback(xhr);
            }
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
}
