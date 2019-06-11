/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * 验证用户名必须2到16位
 * @param {string} str
 * @returns {string}
 */
export function getUsernameMsg(str) {
  if (str.length > 2 && str.length <= 16) {
    return ''
  }
  return '请输入正确的用户名'
}

/**
 * 验证密码必须为6到20位
 * @param str
 * @returns {string}
 */
export function getPasswordMsg(str) {
  if (str.length >= 6 && str.length <= 20) {
    return ''
  }
  return '密码必须6到20位'
}
