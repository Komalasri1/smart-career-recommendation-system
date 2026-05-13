let chartInstance = null;

async function getRecommendation() {

    const name =
        document.getElementById("name").value;

    const skills =
        document.getElementById("skills").value;

    const interests =
        document.getElementById("interests").value;

    document.getElementById("result").innerHTML =

        `
        <div class="result-card">
            <h2>⏳ AI Engine Analyzing Profile...</h2>
        </div>
        `;

    try {

        const response = await fetch(

            "https://carrers-api.onrender.com/career/recommend",

            {
                method:"POST",

                headers:{
                    "Content-Type":"application/json"
                },

                body:JSON.stringify({
                    name,
                    skills,
                    interests
                })
            }
        );

        const data = await response.json();

        let careersHTML = "";

        data.careers.forEach(career => {

            careersHTML += `
                <li>${career}</li>
            `;
        });

        let suggestionsHTML = "";

        data.suggestions.forEach(skill => {

            suggestionsHTML += `
                <li>${skill}</li>
            `;
        });

        document.getElementById("result").innerHTML = `

            <div class="result-card">

                <h2>🎯 AI Career Recommendations</h2>

                <ul>${careersHTML}</ul>

                <h3>
                    📈 Match Score:
                    ${data.score}%
                </h3>

                <h3>💡 Suggested Skills</h3>

                <ul>${suggestionsHTML}</ul>

            </div>
        `;

        const ctx =
            document.getElementById("careerChart");

        if(chartInstance){
            chartInstance.destroy();
        }

        chartInstance = new Chart(ctx, {

            type:"doughnut",

            data:{

                labels:[
                    "Skill Match",
                    "Improvement"
                ],

                datasets:[{

                    data:[
                        data.score,
                        100-data.score
                    ],

                    backgroundColor:[
                        "#3b82f6",
                        "#1e293b"
                    ],

                    borderWidth:0
                }]
            }
        });

    }

    catch(error){

        console.log(error);

        document.getElementById("result").innerHTML =

            `
            <div class="result-card">

                <h2>
                    ❌ Recommendation Failed
                </h2>

            </div>
            `;
    }
}

async function uploadResume() {

    try {

        const fileInput =
            document.getElementById("resumeFile");

        if(fileInput.files.length === 0){

            alert("Please upload a PDF");

            return;
        }

        const formData = new FormData();

        formData.append(
            "file",
            fileInput.files[0]
        );

        document.getElementById("result").innerHTML =

            `
            <div class="result-card">

                <h2>
                    📄 AI Resume Analysis Running...
                </h2>

            </div>
            `;

        const response = await fetch(

            "https://carrers-api.onrender.com/resume/upload",

            {
                method:"POST",
                body:formData
            }
        );

        const data = await response.json();

        document.getElementById("result").innerHTML =

            `
            <div class="result-card">

                <h2>
                    📄 Resume Analysis Result
                </h2>

                <h3>
                    🎯 Recommended Career:
                    ${data.recommendation}
                </h3>

            </div>
            `;
    }

    catch(error){

        console.log(error);

        document.getElementById("result").innerHTML =

            `
            <div class="result-card">

                <h2>
                    ❌ Resume Upload Failed
                </h2>

            </div>
            `;
    }
}