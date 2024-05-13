/** 数字增涨动画
 * from：起始数字
 // to：最终数字
 // duration：动画延迟间隔
 onProgress：增长回调函数*/
export const useNumberAnimation = (options: {
  from: number;
  to: number;
  duration: number;
  onProgress?: (v: number) => void;
}) => {
  const { from, to, duration, onProgress } = options;
  let value: number = from;
  const speed = (to - from) / duration;
  const startTime = Date.now();
  const run = () => {
    const t = Date.now() - startTime;
    if (t >= duration) {
      value = to;
      onProgress?.(value);
      return;
    }
    //当前值=起始值+时间间隔*增长速度
    value = from + t * speed;
    onProgress?.(value);
    //继续执行
    requestAnimationFrame(run);
  };
  run();
};
/**
 * 用法：
 * import
 * userNumberAnimation({
 *   from: 0,
 *   to: 3333333,
 *   duration: 3000,
 *   onProgress: v => {
 *    console.log("此时的值是：${v}");
 *    num.value = v.toFixed(2);
 *   },
 * });
 */
