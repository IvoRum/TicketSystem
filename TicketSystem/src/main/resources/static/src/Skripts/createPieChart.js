function createPieChart(canvasId, data, colors) {
    const canvas = document.getElementById(canvasId);
    const ctx = canvas.getContext('2d');
    const total = data.reduce((sum, value) => sum + value, 0);
    let startAngle = 0;
    let endAngle = 0;
    
    for (let i = 0; i < data.length; i++) {
      endAngle += (Math.PI * 2) * (data[i] / total);
      ctx.beginPath();
      ctx.fillStyle = colors[i];
      ctx.moveTo(canvas.width / 2, canvas.height / 2);
      ctx.arc(canvas.width / 2, canvas.height / 2, canvas.width / 2, startAngle, endAngle);
      ctx.closePath();
      ctx.fill();
      startAngle = endAngle;
    }
  }
  