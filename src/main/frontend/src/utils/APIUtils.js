export const API_BASE_URL = "/api/v1";

const request = async (options) => {
  const headers = new Headers({
    "Content-Type": "application/json",
  });

  const defaults = { headers: headers };
  options = Object.assign({}, defaults, options);

  const response = await fetch(options.url, options);
    const json = await response.json();
    if (!response.ok) {
        return Promise.reject(json);
    }
    return json;
}

export function signin(loginRequestDto){
    return request({
        url: API_BASE_URL + "/user/signin",
        method: 'POST',
        body:  JSON.stringify(loginRequestDto)
    });
}