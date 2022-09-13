/**
 * @param {number} n
 * @return {number}
 */
var climbStairs = function (n) {
    let stairs = [0, 1, 2]
    for (let i = 3; i <= n; i++) {
        stairs.push(stairs[i - 2] + stairs[i - 1]);
    }
    return stairs[n];
};