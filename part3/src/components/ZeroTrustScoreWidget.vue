<template>
  <v-container>
    <!-- Loop through each company to display the widget -->
    <v-card
      v-for="(company, index) in companies"
      :key="index"
      class="pa-4 mb-5"
      elevation="4"
      style="border-radius: 16px;"
    >
      <v-row justify="center">
        <v-col cols="12" sm="6" class="text-center">
          <v-card-title class="font-weight-bold mb-2" style="font-size: 1.5rem;">
            {{ company.companyName }}
          </v-card-title>
          <v-card-subtitle class="mb-4 text-subtitle-1">Overall Zero-Trust Score</v-card-subtitle>
          <v-progress-circular
            :value="company.ZeroTrustScore"
            :size="120"
            :width="15"
            color="primary"
            rotate="270"
            class="mx-auto"
          >
            <span style="font-size: 1.25rem;">{{ company.ZeroTrustScore }}</span>
          </v-progress-circular>
        </v-col>
      </v-row>

      <!-- Metrics Section -->
      <v-card class="pa-4 mt-4" style="background-color: #f7f9fc; border-radius: 12px;">
        <v-card-title class="text-h6 font-weight-bold">Metrics</v-card-title>
        <v-divider class="mb-4"></v-divider>
        <v-row>
          <v-col cols="12" v-for="(metric, index) in getMetrics(company)" :key="index">
            <v-row justify="space-between" class="align-center mb-3">
              <span>{{ metric.label }}: <strong>{{ metric.value.toFixed(0) }}%</strong></span>
              <v-progress-linear
                :value="metric.value"
                color="blue lighten-1"
                striped
                height="15"
                style="width: 60%; border-radius: 8px;"
              ></v-progress-linear>
            </v-row>
          </v-col>
        </v-row>
      </v-card>

      <!-- Risk Category -->
      <v-card class="pa-4 mt-4">
        <v-card-title class="text-h6 font-weight-bold">Risk Category</v-card-title>
        <v-alert
          :type="getRiskCategoryColor(company.riskCategory)"
          border="left"
          prominent
          class="ma-2"
          dense
          outlined
          style="font-size: 1.15rem;"
        >
          {{ company.riskCategory }}
        </v-alert>
      </v-card>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      companies: null,
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const response = await fetch('/data/zero-trust-data.json');
        if (!response.ok) throw new Error('Failed to load JSON data');
        const jsonData = await response.json();
        this.companies = jsonData.companies;
      } catch (error) {
        console.error('Error loading data:', error);
      }
    },
    getMetrics(company) {
      return [
        {
          label: "Shannon Entropy Score",
          value: (company.metrics.ShannonEntropyScore / 10) * 100,
        },
        {
          label: "Certificate Bit Strength",
          value: (company.metrics.certificateBitStrength / 4096) * 100,
        },
        {
          label: "Open Ports Detected",
          value: (1 - company.metrics.openPortsDetected / 100) * 100,
        },
        {
          label: "Firewall Detected",
          value: company.metrics.firewallDetected ? 100 : 0,
        },
        {
          label: "DNSsec Enabled",
          value: company.metrics.DNSsecEnabled ? 100 : 0,
        },
        {
          label: "TLS Version",
          value: company.metrics.tlsVersion === "1.3" ? 100 : 50,
        },
      ];
    },
    getRiskCategoryColor(riskCategory) {
      switch (riskCategory) {
        case "Low Risk":
          return "success";
        case "Moderate Risk":
          return "warning";
        case "High Risk":
          return "error";
        default:
          return "info";
      }
    }
  },
};
</script>

<style scoped>
.v-container {
  padding-top: 20px;
}

.v-card {
  background-color: white;
}

.v-progress-linear {
  transition: width 0.6s ease-in-out;
}

.v-card-title {
  font-weight: bold;
}
</style>
