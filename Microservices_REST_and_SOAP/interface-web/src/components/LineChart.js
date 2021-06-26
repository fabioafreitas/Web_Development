import { Line, mixins } from "vue-chartjs";

export default {
  mixins: [mixins.reactiveProp],
  extends: Line,
  props: ['label', 'chartData', 'options', 'chartColors'],

  computed: {
    data: function () {
      return this.chartData;
    }
  },

  mounted() {
    this.renderLineChart();
  },

  methods: {
    renderLineChart() {
      const dates = this.chartData.map(d => d.date);
      const totals = this.chartData.map(d => d.total);

      const {
        borderColor,
        pointBorderColor,
        pointBackgroundColor,
        backgroundColor
      } = this.chartColors;

      this.renderChart(
        {
          labels: dates,
          datasets: [
            {
              label: this.label,
              data: totals,
              borderColor: borderColor,
              pointBorderColor: pointBorderColor,
              pointBackgroundColor: pointBackgroundColor,
              backgroundColor: backgroundColor
            }
          ]
        },
        this.options
      );
    }
  },

  watch: {
    data: function () {
      this.renderLineChart();
    }
  }
};